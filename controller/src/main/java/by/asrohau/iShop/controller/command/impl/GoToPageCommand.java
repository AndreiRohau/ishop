package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class GoToPageCommand implements Command {
    private final static Logger logger = LoggerFactory.getLogger(GoToPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info(GO_TO_PAGE_COMMAND);
        try {
            String goToPage = INDEX.equals(request.getParameter(ADDRESS)) ? INDEX :
                    "/jsp/" + request.getSession().getAttribute(ROLE) + "/" + request.getParameter(ADDRESS);

            request.getSession().setAttribute(LAST_COMMAND,
                    GO_TO_PAGE_ + request.getParameter(ADDRESS));
            request.getRequestDispatcher(goToPage).forward(request, response);
        } catch (IOException | ServletException e) {
            throw new ControllerException(e);
        }
    }
}

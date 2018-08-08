package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.ControllerFinals;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class GoToPageCommand implements Command {
    private final static Logger logger = Logger.getLogger(GoToPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info(GO_TO_PAGE_COMMAND.inString);
        logger.info("Acting as: " + request.getSession().getAttribute(ROLE.inString));
        try {
            String goToPage = INDEX.inString.equals(request.getParameter(ADDRESS.inString)) ? INDEX.inString :
                    "/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/" + request.getParameter(ADDRESS.inString);

            request.getSession().setAttribute(LAST_COMMAND.inString,
                    GO_TO_PAGE_.inString + request.getParameter(ADDRESS.inString));
            request.getRequestDispatcher(goToPage).forward(request, response);
        } catch (IOException | ServletException e) {
            throw new ControllerException(e);
        }
    }
}

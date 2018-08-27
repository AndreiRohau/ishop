package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class GoToPageCommand extends AbstractCommand {
    private final static Logger logger = LoggerFactory.getLogger(GoToPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to GoToPageCommand");
        try {
            String goToPage = "index.jsp".equals(request.getParameter(ADDRESS)) ? "index.jsp" :
                    "/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/" + request.getParameter(ADDRESS);

            request.setAttribute(MESSAGE, request.getParameter(MESSAGE));
            request.getSession().setAttribute(LAST_COMMAND, defineCommand(request, true));
            request.getRequestDispatcher(goToPage).forward(request, response);
        } catch (IOException | ServletException e) {
            throw new ControllerException(e);
        }
    }
}

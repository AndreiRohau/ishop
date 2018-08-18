package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowUsersCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ShowUsersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowUsersCommand");
        try {
            int currentPage = Integer.parseInt(request.getParameter(PAGE));
            int maxPage = (int) Math.ceil(((double) userService.countUsers()) / MAX_ROWS_AT_PAGE);
            int row = (currentPage - 1) * MAX_ROWS_AT_PAGE;

            List<User> users = userService.getAllUsers(row);

            request.setAttribute("users", users);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE,
                    "FrontController?command=showUsers&page=");
            request.getSession().setAttribute(LAST_COMMAND,
                    "FrontController?command=showUsers&page=" + currentPage);

            request.getRequestDispatcher("/WEB-INF/jsp/admin/users.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.Page;
import by.asrohau.iShop.entity.User;
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

public class ShowUsersCommand extends AbstractCommand {

    private static final Logger logger = LoggerFactory.getLogger(ShowUsersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowUsersCommand");
        try {
            Page page = new Page(request.getParameter(PAGE), userService.countUsers());
            List<User> users = userService.getUsers(page.getRow());

            request.setAttribute("page", page);
            request.setAttribute("users", users);
            String lastCommandNeedPage = defineCommand(request, false);
            request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE, lastCommandNeedPage);
            request.getSession().setAttribute(LAST_COMMAND, lastCommandNeedPage + page.getCurrentPage());

            request.getRequestDispatcher("/WEB-INF/jsp/" +
                    request.getSession().getAttribute(ROLE) +
                    "/users.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

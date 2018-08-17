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

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class UpdateUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to UpdateUserCommand");
        try {
            User user  = new User(Integer.parseInt(request.getParameter(ID)),
                    request.getParameter(LOGIN),
                    request.getParameter(PASSWORD));

            if(!userService.updateUser(user)){
                request.setAttribute("updateFailed", true);
                user = userService.findUserWithId(user);
            }

            request.setAttribute("user", user);
            request.getSession().setAttribute(LAST_COMMAND, "FrontController?command=userInfo&id=" + request.getParameter("id"));
            request.getRequestDispatcher("/jsp/admin/userInfo.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
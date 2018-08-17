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

public class UserInfoCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to UserInfoCommand");
        try {
            User user  = new User(Integer.parseInt(request.getParameter(ID)));
            user = userService.findUserWithId(user);

            if(user != null){
                request.setAttribute("user", user);
                request.getSession().setAttribute(LAST_COMMAND,
                        "FrontController?command=userInfo&id=" + request.getParameter(ID));
            } else {
                request.setAttribute("cannotFindUser", true);
            }

            request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE)
                    + "/userInfo.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class UserInfoCommand implements Command {

    private static final Logger logger = Logger.getLogger(UserInfoCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to UserInfoCommand");
        try {
            User user  = new User(Integer.parseInt(request.getParameter(ID.inString)));
            user = userService.findUserWithId(user);

            if(user != null){
                request.setAttribute("user", user);
                request.getSession().setAttribute(LAST_COMMAND.inString,
                        "FrontController?command=userInfo&id=" + request.getParameter(ID.inString));
            } else {
                request.setAttribute("cannotFindUser", true);
            }

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE.inString)
                    + "/userInfo.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

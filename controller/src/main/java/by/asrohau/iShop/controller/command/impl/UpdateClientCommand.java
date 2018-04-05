package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateClientCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to UpdateClientCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        String lastCMD;
        String goToPage;
        User user  = new User(Integer.parseInt(request.getParameter("id")),
                request.getParameter("login"),
                request.getParameter("password"));

        try {
            if(!userService.updateUser(user)){
                request.setAttribute("updateFailed", "Update failed");
                user = userService.findUserWithId(user);
            }
            request.setAttribute("userToEdit", user);
            lastCMD = "FrontController?command=editClient&userId=" + request.getParameter("id");
            goToPage = "/jsp/admin/editClient.jsp";

            request.getSession().setAttribute("lastCMD", lastCMD);
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
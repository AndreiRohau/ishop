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

public class EditClientCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to EditClientCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        String lastCMD;
        String goToPage;
        User user  = new User();
        user.setId(Integer.parseInt(request.getParameter("userId")));
        try {
            user = userService.findUserWithId(user);
            if(user != null){
                request.setAttribute("userToEdit", user);
                goToPage = "/jsp/admin/editClient.jsp";
                lastCMD = "FrontController?command=editClient&userId=" + request.getParameter("userId");

            } else {
                request.setAttribute("errorMessage", "Probably user does not exists!");
                goToPage = "error.jsp";
                lastCMD = "FrontController?command=goToPage&address=manageClients.jsp";
            }
            request.getSession().setAttribute("lastCMD", lastCMD);
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

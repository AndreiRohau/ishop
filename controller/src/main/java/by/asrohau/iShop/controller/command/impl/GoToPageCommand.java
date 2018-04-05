package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to GoToPageCommand");

        try {
            String goToPage;

            if (request.getSession().getAttribute("userName") != null &&
                    !request.getParameter("address").matches("index.jsp") &&
                    !request.getParameter("address").matches("error.jsp")) {

                if(request.getSession().getAttribute("userName").equals("Admin")) {
                    goToPage = "/jsp/admin/" + request.getParameter("address");
                } else {
                    goToPage = "/jsp/user/" + request.getParameter("address");
                }
                System.out.println("Acting as: " + request.getSession().getAttribute("userName"));
            } else {
                goToPage = "index.jsp";
            }

            request.getSession().setAttribute("lastCMD", "FrontController?command=goToPage&address=" + request.getParameter("address"));
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            throw new ControllerException(e);
        }
    }
}

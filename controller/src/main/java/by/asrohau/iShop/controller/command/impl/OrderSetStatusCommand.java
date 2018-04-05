package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderSetStatusCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to OrderSetStatusCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        String status = request.getParameter("new_status").split("Set")[1].toLowerCase();
        try {
            String message;
            if (orderService.orderSetStatus(Integer.parseInt(request.getParameter("orderId")), status)) {
                message = "You have Setted ORDER as " + status + " successfully";
            } else {
                message = "Can NOT SET this order as " + status + ", try again!";
            }

            if(request.getParameter("from").matches("manageOrders")){
                response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                        + "&msg=" + message);
            }else{
                request.getSession().setAttribute("lastCMD",
                        "FrontController?command=goToPage&address=manageOrders.jsp");
                request.getRequestDispatcher("/jsp/admin/manageOrders.jsp").forward(request, response);
            }

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
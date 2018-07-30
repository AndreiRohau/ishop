package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to DeleteOrderCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            String message;
            Order order = new Order();
            order.setId(Integer.parseInt(request.getParameter("orderId")));
            if (orderService.deleteOrder(order)) {
                message = "You have REMOVED new ORDER successfully";
                System.out.println(message);
            } else {
                message = "Can NOT remove this order, try again!";
                System.out.println(message);
            }

            if(request.getParameter("from").matches("manageOrders")) {
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

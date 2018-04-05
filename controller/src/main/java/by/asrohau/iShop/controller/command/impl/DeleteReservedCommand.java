package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteReservedCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to DeleteReservedCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        User user = new User();

        try {
            String message;
            if (orderService.deleteReserved(Integer.parseInt(request.getParameter("reserveId")))) {
                message = "You have REMOVED product successfully";
                System.out.println(message);
            } else {
                message = "Can NOT remove this product, try again!";
                System.out.println(message);
            }

            response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                    + "&msg=" + message);

        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

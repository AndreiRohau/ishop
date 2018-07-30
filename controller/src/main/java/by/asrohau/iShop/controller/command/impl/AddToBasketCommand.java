package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static by.asrohau.iShop.controller.ControllerFinals.*;

public class AddToBasketCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to AddToBasketCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();

        Reserve reserve;
        User user = new User();

        try {
            user.setLogin((String) request.getSession().getAttribute(USER_NAME.inString));
            UserDTO userDTO = userService.findIdWithLogin(user);
            int uid = userDTO.getId();
            reserve = new Reserve(uid,
                    Integer.parseInt(request.getParameter("productId")));
            String message;
            if (orderService.saveReserve(reserve)) {
                message = "You have added new product successfully";
            } else {
                message = "Can NOT add this product, try again!";
            }
            response.sendRedirect(String.valueOf(request.getSession(true).getAttribute(LAST_COMMAND.inString))
                    + "&msg=" + message);

        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

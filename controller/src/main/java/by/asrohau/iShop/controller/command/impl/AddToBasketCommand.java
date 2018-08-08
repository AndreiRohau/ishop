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
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.ID;
import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;
import static by.asrohau.iShop.controller.ControllerFinals.LOGIN;

public class AddToBasketCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddToBasketCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to AddToBasketCommand");
        try {
            User user = new User((String) request.getSession().getAttribute(LOGIN.inString));
            UserDTO userDTO = userService.findUserDTOWithLogin(user);

            Reserve reserve = new Reserve(userDTO.getId(), Integer.parseInt(request.getParameter(ID.inString)));

            boolean success = orderService.saveReserve(reserve);

            response.sendRedirect(String.valueOf(request.getSession().getAttribute(LAST_COMMAND.inString))
                    + "&success=" + success);
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

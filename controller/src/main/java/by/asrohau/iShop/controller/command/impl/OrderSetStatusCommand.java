package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class OrderSetStatusCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderSetStatusCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to OrderSetStatusCommand");
        try {
            boolean statusChanged = orderService.orderSetStatus(
                    Long.parseLong(request.getParameter(ID)),
                    request.getParameter(STATUS));

            request.setAttribute(MESSAGE, statusChanged);
            response.sendRedirect((String) request.getSession().getAttribute(LAST_COMMAND));
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
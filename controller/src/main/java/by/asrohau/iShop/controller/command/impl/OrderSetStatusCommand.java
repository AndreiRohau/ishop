package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
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
            String status = "";
            switch (request.getParameter(STATUS)) {
                case "new": status = ACTIVE;
                            break;
                case "active": status = CLOSED;
                            break;
                case "closed": status = NEW;
                            break;
            }

            request.setAttribute(MESSAGE, orderService.orderSetStatus(Long.parseLong(request.getParameter(ID)), status));
            request.getRequestDispatcher((String) request.getSession().getAttribute(LAST_COMMAND))
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
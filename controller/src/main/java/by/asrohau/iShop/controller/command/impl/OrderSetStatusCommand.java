package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.controller.ControllerFinals;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class OrderSetStatusCommand implements Command {

    private static final Logger logger = Logger.getLogger(OrderSetStatusCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to OrderSetStatusCommand");
        try {
            String status = "";
            switch (request.getParameter(STATUS.inString)) {
                case "new": status = ACTIVE.inString;
                            break;
                case "active": status = CLOSED.inString;
                            break;
                case "closed": status = NEW.inString;
                            break;
            }
            Order order = new Order(Integer.parseInt(request.getParameter(ID.inString)));

            request.setAttribute(MESSAGE.inString, orderService.orderSetStatus(order, status));
            request.getRequestDispatcher((String) request.getSession().getAttribute(LAST_COMMAND.inString))
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
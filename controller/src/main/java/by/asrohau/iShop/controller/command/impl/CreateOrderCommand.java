package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.ID;
import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;

public class CreateOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(CreateOrderCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();
    private ReserveService reserveService= serviceFactory.getReserveService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to CreateOrderCommand");
        try{
            long userId = (Long) request.getSession().getAttribute(ID);
            List<Long> reservedProductIds = reserveService.getReservedProductIds(userId);
            Order order = new Order(userId, request.getParameter("userAddress"), request.getParameter("userPhone"));
            boolean orderCreated = orderService.saveNewOrder(order, reservedProductIds);

            request.getSession().setAttribute(LAST_COMMAND, "FrontController?command=showReserved&page=1&orderCreated=" + orderCreated);
            response.sendRedirect("FrontController?command=showReserved&page=1&orderCreated=" + orderCreated);
        } catch (ServiceException  | IOException e) {
            throw new ControllerException(e);
        }
    }
}

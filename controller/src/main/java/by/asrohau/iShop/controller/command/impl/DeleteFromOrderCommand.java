package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;

public class DeleteFromOrderCommand extends AbstractCommand {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFromOrderCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to DeleteFromOrderCommand");
        try {
            Order order = orderService.findOrderById(Long.parseLong(request.getParameter("orderId")));
            boolean productRemoved = orderService.removeProductFromOrder(
                    order,
                    request.getParameter("currentPage"),
                    request.getParameter("indexRemovingProduct"));

            if("".equals(order.getProductIds()) && orderService.deleteOrder(order.getId())) {
                response.sendRedirect("FrontController?command=showUserOrders&page=1");
            }else {
                response.sendRedirect((String) request.getSession().getAttribute(LAST_COMMAND));
            }
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

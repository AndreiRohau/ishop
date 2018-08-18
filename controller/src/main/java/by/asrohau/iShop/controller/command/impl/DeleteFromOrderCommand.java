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

import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;
import static by.asrohau.iShop.controller.ControllerFinals.MAX_ROWS_AT_PAGE;

public class DeleteFromOrderCommand implements Command{

    private static final Logger logger = LoggerFactory.getLogger(DeleteFromOrderCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to DeleteFromOrderCommand");
        try {
            Order order = orderService.findOrderById(Long.parseLong(request.getParameter("orderId")));
            String[] productIdsArray = order.getProductIds().split(",");

            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int indexRemovingProduct = (currentPage - 1) * MAX_ROWS_AT_PAGE
                    + Integer.parseInt(request.getParameter("indexRemovingProduct"));

            StringBuilder finalIds = new StringBuilder();

            for(int i = 1; i <= productIdsArray.length; i++) {
                if(i != indexRemovingProduct) {
                    finalIds.append(productIdsArray[i - 1]).append(",");
                }
            }

            order.setProductIds(finalIds.toString());

            if("".equals(order.getProductIds()) && orderService.deleteOrder(order)) {
                //orderService.deleteOrder(order) // went up ^^^^
                request.getSession().setAttribute(LAST_COMMAND, "FrontController?command=showOrders&page=1");
                request.getRequestDispatcher("FrontController?command=showOrders&page=1").forward(request, response);
            }else {
                response.sendRedirect(String.valueOf(request.getSession().getAttribute(LAST_COMMAND))
                        + "&productDeleted=" + orderService.deleteProductFromOrder(order) + "&orderId=" + order.getId());
            }
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

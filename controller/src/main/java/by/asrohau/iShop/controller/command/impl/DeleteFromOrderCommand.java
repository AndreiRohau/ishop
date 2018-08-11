package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
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

public class DeleteFromOrderCommand implements Command{

    private static final Logger logger = Logger.getLogger(DeleteFromOrderCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to DeleteFromOrderCommand");
        try {
            Order order = new Order(Integer.parseInt(request.getParameter("orderId")));
            String[] productIdsArray = order.getProductIds().split(",");

            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int indexOfDeletingProduct = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString)
                    + Integer.parseInt(request.getParameter("indexRemovingProduct"));

            StringBuilder finalIds = new StringBuilder();
            ///////////////////
            int indexRemovingProduct = 1;
            for(String id : productIdsArray){
                if(indexRemovingProduct != indexOfDeletingProduct && indexRemovingProduct < productIdsArray.length) {
                    finalIds.append(id).append(",");
                }
                indexRemovingProduct++;
            }

            // simplified method!!!!!!!!!!!!!!!
//            for(int i = 1; i != indexOfDeletingProduct && i < productIdsArray.length; i++) {
//                if(indexRemovingProduct != indexOfDeletingProduct && indexRemovingProduct < productIdsArray.length) {
//                    finalIds.append(productIdsArray[i - 1]).append(",");
//                }
//            }


            order.setProductIds(finalIds.toString());

            if("".equals(order.getProductIds())) {
                orderService.deleteOrder(order);
                request.getSession().setAttribute(LAST_COMMAND.inString, "FrontController?command=showOrders&page=1");
                request.getRequestDispatcher("FrontController?command=showOrders&page=1").forward(request, response);
            }else {
                response.sendRedirect(String.valueOf(request.getSession().getAttribute(LAST_COMMAND.inString))
                        + "&productDeleted=" + orderService.deleteProductFromOrder(order) + "&orderId=" + order.getId());
            }
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

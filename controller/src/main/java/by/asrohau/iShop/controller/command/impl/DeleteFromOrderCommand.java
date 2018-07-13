package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFromOrderCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to DeleteFromOrderCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        Order order = new Order();
        order.setId(Integer.parseInt(request.getParameter("orderId")));

        int currentPage;

        String productIDsString = request.getParameter("productIDsString");
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int indexRemovingProduct =  Integer.parseInt(request.getParameter("indexRemovingProduct"));


        try {

            String[] productIDsArray = productIDsString.split(",");
            int indexOfdeletingProd = (currentPage - 1) * 15 + indexRemovingProduct;

            String finalIDs = "";
            indexRemovingProduct = 1;
            for(String id : productIDsArray){
                if(indexRemovingProduct != indexOfdeletingProd && indexRemovingProduct < productIDsArray.length) {
                    finalIDs = finalIDs + id + ",";
                }
                indexRemovingProduct++;
            }

            order.setProductIds(finalIDs);
            String message = orderService.deleteProductFromOrder(order) ? "You have REMOVED product successfully" :
                    "Can NOT remove this product, try again!";

            if(!finalIDs.equals("")) {
                response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                        + "&msg=" + message + "&orderId=" + order.getId());
            }else {
                orderService.deleteOrder(order.getId());
                request.getSession().setAttribute("lastCMD",
                        "FrontController?command=goToPage&address=manageOrders.jsp");
                request.getRequestDispatcher("/jsp/admin/manageOrders.jsp").forward(request, response);
            }
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }


    }
}

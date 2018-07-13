package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectAllNewOrdersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to SelectAllNewOrdersCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        int currentPage;
        int maxPage;
        int row;
        String status = "new";
        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)*15;

        try {
            //count amount of all NEW orders
            maxPage = (int) Math.ceil(((double) orderService.countOrders(status)) / 15);

            List<Order> newOrdersList = orderService.getAllOrders(row, status); // ArrayList
            request.setAttribute("array", newOrdersList);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("command", "selectAllNewOrders");
            request.setAttribute("command_2", "editNewOrder");
            request.setAttribute("command_3", "orderSetActive");
            request.setAttribute("command_4", "deleteOrder");
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=selectAllNewOrders&page_num=" + currentPage);

            String goToPage = "/jsp/admin/manageOrders.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

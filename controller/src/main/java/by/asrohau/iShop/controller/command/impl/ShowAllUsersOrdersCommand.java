package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.MAX_ROWS_AT_PAGE;

public class ShowAllUsersOrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(ShowAllMyOrdersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowAllClientsOrdersCommand");
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int currentPage = Integer.parseInt(request.getParameter("page"));
            int maxPage = (int) Math.ceil(((double) orderService.countClientOrders(userId)) / Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            int row = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString);

            List<Order> allOrders = orderService.getAllClientsOrders(row, userId); // ArrayList
            request.setAttribute("array", allOrders);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("userId", userId);
            request.setAttribute("command_2", "editNewOrder");
            request.setAttribute("command_3", "orderSetActive");
            request.setAttribute("command_4", "deleteOrder");
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=showAllClientsOrders&page=" + currentPage +
                            "&userId=" + userId);

            request.getRequestDispatcher("/jsp/admin/allClientsOrders.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

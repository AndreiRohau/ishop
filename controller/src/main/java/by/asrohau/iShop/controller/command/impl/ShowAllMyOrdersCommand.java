package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowAllMyOrdersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to ShowAllMyOrdersCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        OrderService orderService = serviceFactory.getOrderService();

        User user = new User();
        int currentPage;
        int maxPage;
        int row;
        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)*15;

        try {
            user.setLogin((String) request.getSession().getAttribute("userName"));
            int userId = userService.findIdWithLogin(user).getId();
            //count amount of all NEW orders
            maxPage = (int) Math.ceil(((double) orderService.countClientOrders(userId)) / 15);

            List<Order> allOrders = orderService.getAllClientsOrders(row, userId); // ArrayList
            request.setAttribute("allOrders", allOrders);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("for_user", "for_user");
            request.setAttribute("userId", userId);
            request.setAttribute("command_2", "editNewOrder");
            request.setAttribute("command_3", "orderSetActive");
            request.setAttribute("command_4", "deleteOrder");
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=showAllMyOrders&page_num=" + currentPage +
                            "&userId=" + userId);

            String goToPage = "/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/orders.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

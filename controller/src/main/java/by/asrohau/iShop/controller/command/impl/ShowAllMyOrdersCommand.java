package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowAllMyOrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(ShowAllMyOrdersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowAllMyOrdersCommand");
        try {
            User user = new User((String) request.getSession().getAttribute(LOGIN.inString));
            int userId = userService.findUserDTOWithLogin(user).getId();

            int currentPage = Integer.parseInt(request.getParameter(PAGE.inString));
            int maxPage = (int) Math.ceil(((double) orderService.countClientOrders(userId)) / Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            int row = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString);

            List<Order> orders = orderService.getAllClientsOrders(row, userId);
            request.setAttribute("orders", orders);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("userId", userId);
            request.setAttribute("command_2", "editNewOrder");
            request.setAttribute("command_3", "orderSetActive");
            request.setAttribute("command_4", "deleteOrder");
            request.getSession().setAttribute(LAST_COMMAND.inString,
                    "FrontController?command=showAllMyOrders&page=" + currentPage +
                            "&userId=" + userId);

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/orders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

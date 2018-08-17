package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowUserOrdersCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ShowUserOrdersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowUserOrdersCommand");
        try {
            User user;
            if (request.getSession().getAttribute(ROLE).equals("user")) {
                user = new User((String) request.getSession().getAttribute(LOGIN));
                user = new User(userService.findUserDTOWithLogin(user).getId(), user.getLogin());
            } else {
                user = userService.findUserWithId(new User(Integer.parseInt(request.getParameter(ID))));
                user.setLogin(request.getParameter(LOGIN));
            }
            Order order = new Order(user.getId(), "any");
            int currentPage = Integer.parseInt(request.getParameter(PAGE));
            int maxPage = (int) Math.ceil(((double) orderService.countUserOrders(order)) / MAX_ROWS_AT_PAGE);
            int row = (currentPage - 1) * MAX_ROWS_AT_PAGE;

            request.setAttribute("orders", orderService.getUserOrders(row, order));
            request.setAttribute("user", user);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND,
                    "FrontController?command=showUserOrders&id=" + user.getId() +
                            "&login=" + user.getLogin() +
                            "&page=" + currentPage);
            request.getSession().setAttribute(LAST_COMMAND_PAGE,
                    "FrontController?command=showUserOrders&id=" + user.getId() +
                            "&login=" + user.getLogin() +
                            "&page=");

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE) + "/userOrders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

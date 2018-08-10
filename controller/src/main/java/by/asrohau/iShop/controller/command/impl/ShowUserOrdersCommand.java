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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;
import static by.asrohau.iShop.controller.ControllerFinals.ROLE;

public class ShowUserOrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(ShowUserOrdersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowUserOrdersCommand");
        try {
            User user;
            if (request.getSession().getAttribute(ROLE.inString).equals("user")) {
                user = new User((String) request.getSession().getAttribute(LOGIN.inString));
                user = new User(userService.findUserDTOWithLogin(user).getId(), user.getLogin());
            } else {
                user = userService.findUserWithId(new User(Integer.parseInt(request.getParameter(ID.inString))));
                user.setLogin(request.getParameter(LOGIN.inString));
            }
            Order order = new Order(user.getId(), "any");
            int currentPage = Integer.parseInt(request.getParameter(PAGE.inString));
            int maxPage = (int) Math.ceil(((double) orderService.countUserOrders(order)) / Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            int row = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString);

            request.setAttribute("orders", orderService.getUserOrders(row, order));
            request.setAttribute("user", user);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND.inString,
                    "FrontController?command=showUserOrders&id=" + user.getId() +
                            "&login=" + user.getLogin() +
                            "&page=" + currentPage);
            request.getSession().setAttribute(LAST_COMMAND_PAGE.inString,
                    "FrontController?command=showUserOrders&id=" + user.getId() +
                            "&login=" + user.getLogin() +
                            "&page=");

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/userOrders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

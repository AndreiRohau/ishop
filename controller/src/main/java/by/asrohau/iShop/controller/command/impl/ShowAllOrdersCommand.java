package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
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
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowAllOrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(ShowAllMyOrdersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowAllOrdersCommand");

        try{
            int currentPage = Integer.parseInt(request.getParameter(PAGE.inString));
            int maxPage = (int) Math.ceil(((double) orderService.countAll()) / Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            int row = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString);

            request.setAttribute("orders", orderService.getAllOrders(row));
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND.inString,
                    "FrontController?command=showAllOrders&page=" + currentPage);
            request.getSession().setAttribute(LAST_COMMAND_PAGE.inString,
                    "FrontController?command=showAllOrders&page=");

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/allOrders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

package by.asrohau.iShop.controller.command.impl;

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

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowOrdersCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ShowOrdersCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowOrdersCommand");

        try{
            int currentPage = Integer.parseInt(request.getParameter(PAGE));
            int maxPage = (int) Math.ceil(((double) orderService.countOrders()) / MAX_ROWS_AT_PAGE);
            int row = (currentPage - 1) * MAX_ROWS_AT_PAGE;

            request.setAttribute("orders", orderService.getOrders(row));
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND,
                    "FrontController?command=showOrders&page=" + currentPage);
            request.getSession().setAttribute(LAST_COMMAND_PAGE,
                    "FrontController?command=showOrders&page=");

            request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/orders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

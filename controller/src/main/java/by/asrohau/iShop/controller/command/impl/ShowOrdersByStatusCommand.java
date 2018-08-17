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

public class ShowOrdersByStatusCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ShowOrdersByStatusCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowOrdersByStatusCommand");

        try{
            String status = request.getParameter(STATUS);
            int currentPage = Integer.parseInt(request.getParameter(PAGE));
            int maxPage = (int) Math.ceil(((double) orderService.countOrdersByStatus(status)) / MAX_ROWS_AT_PAGE);
            int row = (currentPage - 1) * MAX_ROWS_AT_PAGE;

            request.setAttribute("orders", orderService.getOrdersByStatus(row, status));
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND,
                    "FrontController?command=showOrdersByStatus&status=" + status + "&page=" + currentPage);
            request.getSession().setAttribute(LAST_COMMAND_PAGE,
                    "FrontController?command=showOrdersByStatus&status=" + status + "&page=");

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE) + "/orders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

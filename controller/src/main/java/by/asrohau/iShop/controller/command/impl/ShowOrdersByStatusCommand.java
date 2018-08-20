package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.Page;
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
        try {
            String status = request.getParameter(STATUS);
            Page page = new Page(request.getParameter(PAGE), orderService.countOrdersByStatus(status));

            request.setAttribute("orders", orderService.getOrdersByStatus(page.getRow(), status));
            request.setAttribute("page", page);
            String lastCommand = "FrontController?command=showOrdersByStatus&status=" + status + "&page=";
            request.getSession().setAttribute(LAST_COMMAND, lastCommand + page.getCurrentPage());
            request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE, lastCommand);

            request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/orders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Page;
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
                user = userService.findUserWithId((Long) request.getSession().getAttribute(ID));
            } else {
                user = userService.findUserWithId(Long.parseLong(request.getParameter(ID)));
            }

            Page page = new Page(request.getParameter(PAGE), orderService.countUserOrders(user.getId()));

            request.setAttribute("orders", orderService.getUserOrders(page.getRow(), user.getId()));
            request.setAttribute("user", user);
            request.setAttribute("page", page);
            String lastCommandNeedPage = "FrontController?command=showUserOrders" +
                    "&id=" + user.getId() +
                    "&login=" + user.getLogin() +
                    "&page=";
            request.getSession().setAttribute(LAST_COMMAND, lastCommandNeedPage + page.getCurrentPage());
            request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE, lastCommandNeedPage);

            request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/userOrders.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

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

public class CreateOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(CreateOrderCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info(CREATE_ORDER_COMMAND.inString);
        try{
            User user = new User((String) request.getSession().getAttribute(LOGIN.inString));
            user.setId(userService.findUserDTOWithLogin(user).getId());
            StringBuilder productIds = new StringBuilder();
            for(int id : orderService.getAllReservedIds(user.getId())){
                productIds.append(String.valueOf(id)).append(",");
            }

            Order order = new Order(user.getId(),
                    productIds.toString(),
                    request.getParameter("userAddress"),
                    request.getParameter("userPhone"),
                    NEW.inString);

            if (orderService.saveNewOrder(order)) {
                orderService.deleteAllReserved(user.getId());
                request.setAttribute(MESSAGE.inString, true);
            } else {
                request.setAttribute(ERROR_MESSAGE.inString, false);
            }

            request.setAttribute(LAST_COMMAND.inString, "FrontController?command=selectAllReserved&page=1");
            request.getRequestDispatcher("/jsp/user/basket.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

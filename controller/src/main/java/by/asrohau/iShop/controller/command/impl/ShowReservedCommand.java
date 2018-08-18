package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import by.asrohau.iShop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowReservedCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ShowReservedCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ReserveService reserveService= serviceFactory.getReserveService();
    private UserService userService = serviceFactory.getUserService();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowReservedCommand");
        try {
            long userId = (Long) request.getSession().getAttribute(ID);
            int currentPage = Integer.parseInt(request.getParameter(PAGE));
            int row = (currentPage - 1) * MAX_ROWS_AT_PAGE;
            int maxPage = (int) Math.ceil(((double) reserveService.countReserved(userId)) / MAX_ROWS_AT_PAGE);

            List<Reserve> reservations = reserveService.getAllReserved(userId, row);
            List<Product> products = new ArrayList<>();

            for(Reserve reservation : reservations){
                Product product = productService.findProductWithId(reservation.getProductId());
                product.setReserveId(reservation.getId());
                products.add(product);
            }

            request.setAttribute("products", products);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("orderCreated", request.getParameter("orderCreated"));
            request.getSession().setAttribute(LAST_COMMAND,
                    "FrontController?command=showReserved&page=" + currentPage);
            request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE,
                    "FrontController?command=showReserved&page=");

            request.getRequestDispatcher("/WEB-INF/jsp/user/basket.jsp").forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

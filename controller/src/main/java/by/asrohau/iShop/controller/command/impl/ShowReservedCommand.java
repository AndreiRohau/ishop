package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowReservedCommand implements Command {

    private static final Logger logger = Logger.getLogger(ShowReservedCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ReserveService reserveService= serviceFactory.getReserveService();
    private UserService userService = serviceFactory.getUserService();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowReservedCommand");
        try {
            User user = new User((String) request.getSession().getAttribute(LOGIN.inString));
            long userId = userService.findUserDTOWithLogin(user).getId();

            int currentPage = Integer.parseInt(request.getParameter(PAGE.inString));
            int row = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString);
            int maxPage = (int) Math.ceil(((double) reserveService.countReserved(userId)) / Integer.parseInt(MAX_ROWS_AT_PAGE.inString));

            List<Product> reservedIds = reserveService.getAllReserved(userId, row);
            List<Product> products = new ArrayList<>();
            for(Product p : reservedIds){
                Product product = productService.findProductWithId(p);
                product.setReserveId(p.getReserveId());
                products.add(product);
                product = new Product();
            }

            request.setAttribute("products", products);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND.inString,
                    "FrontController?command=showReserved&page=" + currentPage);
            request.getSession().setAttribute(LAST_COMMAND_PAGE.inString,
                    "FrontController?command=showReserved&page=");

            request.getRequestDispatcher("/jsp/user/basket.jsp").forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}



package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class OrderInfoCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();
    private OrderService orderService = serviceFactory.getOrderService();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to OrderInfoCommand");
        try {
            Order order = orderService.findOrderWithID(new Order(Integer.parseInt(request.getParameter(ID))));
            User user = userService.findUserWithId(new User(order.getUserId()));

            //find and get all prod ids from order; put into  String[] as [x, x, x ...]
            String[] productIdsArray = order.getProductIds().split(",");

            //finding maxPage
            int currentPage = Integer.parseInt(request.getParameter(PAGE));
            int maxPage = (int) Math.ceil(((double) productIdsArray.length) / MAX_ROWS_AT_PAGE);
            int row = (currentPage - 1) * MAX_ROWS_AT_PAGE;

            //due to currentPage get productIDsArray part if(1)[1-15] - if(2)[16-30] - if(3)[31-45] - if(4)[46-48]...
            int finArrlength = (currentPage < maxPage) || (productIdsArray.length % MAX_ROWS_AT_PAGE) == 0 ?
                    MAX_ROWS_AT_PAGE : productIdsArray.length % MAX_ROWS_AT_PAGE;

            int[] productIDs = new int[finArrlength];
            for(int i = 0; i < finArrlength; i++){
                productIDs[i] = Integer.parseInt(productIdsArray[i + row]);
            }

            //find each product. create an arraylist
            List<Product> products = new ArrayList<>();
            for(int id : productIDs){
                Product product = new Product();
                product.setId(id);
                product = productService.findProductWithId(product);
                product.setOrderId(order.getId());
                products.add(product);
            }

            request.setAttribute("order", order);
            request.setAttribute("products", products);
            request.setAttribute("user", user);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute(LAST_COMMAND,
                    "FrontController?command=orderInfo"
                            + "&id=" + order.getId()
                            + "&userId=" + order.getUserId()
                            + "&address=" + order.getUserAddress()
                            + "&phone=" + order.getUserPhone()
                            + "&page=" + currentPage);

            request.getSession().setAttribute(LAST_COMMAND_PAGE,
                    "FrontController?command=orderInfo"
                            + "&id=" + order.getId()
                            + "&userId=" + order.getUserId()
                            + "&address=" + order.getUserAddress()
                            + "&phone=" + order.getUserPhone()
                            + "&page=");

            request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/orderInfo.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
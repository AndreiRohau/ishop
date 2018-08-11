

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class OrderInfoCommand implements Command {

    private static final Logger logger = Logger.getLogger(OrderInfoCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to OrderInfoCommand");
        try {
            User user = new User((String) request.getSession().getAttribute(LOGIN.inString));
            Order order = orderService.findOrderWithID(new Order(Integer.parseInt(request.getParameter(ID.inString))));

            //find and get all prod ids from order; put into  String[] as [x, x, x ...]
            String[] productIdsArray = order.getProductIds().split(",");

            //finding maxPage
            int currentPage= Integer.parseInt(request.getParameter(PAGE.inString));
            int maxPage  = (int) Math.ceil(((double) productIdsArray.length) / Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            int row = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString);

            //due to currentPage get productIDsArray part if(1)[1-15] - if(2)[16-30] - if(3)[31-45]......
            int finArrlength = currentPage < maxPage ?
                    Integer.parseInt(MAX_ROWS_AT_PAGE.inString) :
                    (productIdsArray.length % Integer.parseInt(MAX_ROWS_AT_PAGE.inString) == 0 ?
                            Integer.parseInt(MAX_ROWS_AT_PAGE.inString) :
                            productIdsArray.length % Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            int[] productIDs = new int[finArrlength];
            for(int i = 0; i < finArrlength; i++){
                productIDs[i] = Integer.parseInt(productIdsArray[i+row]);
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
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=orderInfo&page=" + currentPage
                            + "&id=" + order.getId()
                            + "&userId=" + order.getUserId()
                            + "&address=" + order.getUserAddress()
                            + "&phone=" + order.getUserPhone()
                            + "&new_status=" + String.valueOf(request.getParameter("new_status"))
                            + "&from=" + request.getParameter("from"));

            request.setAttribute("lastCMDneedPage",
                    "FrontController?command=orderInfo"
                            + "&id=" + order.getId()
                            + "&userId=" + order.getUserId()
                            + "&address=" + order.getUserAddress()
                            + "&phone=" + order.getUserPhone()
                            + "&new_status=" + String.valueOf(request.getParameter("new_status"))
                            + "&from=" + request.getParameter("from")
                            + "&page=");

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/orderInfo.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
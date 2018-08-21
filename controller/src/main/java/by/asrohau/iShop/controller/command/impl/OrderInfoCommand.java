

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Page;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class OrderInfoCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProductService productService = serviceFactory.getProductService();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to OrderInfoCommand");
        try {
            Order order = orderService.findOrderById(Long.parseLong(request.getParameter(ID)));
            List<Product> AllProducts = productService.findProductsByOrder(order);
            Page page = new Page(request.getParameter(PAGE), AllProducts.size());

            if(AllProducts.size() == 0) {
                orderService.deleteOrder(order.getId());
                response.sendRedirect("FrontController?command=showUserOrders&page=1&id=" + order.getUserId());
            } else {
                request.setAttribute("order", order);
                request.setAttribute("products", productService.subList(AllProducts, page));
                request.setAttribute("page", page);
                String lastCommand = "FrontController?command=orderInfo"
                        + "&id=" + order.getId()
                        + "&userId=" + order.getUserId()
                        + "&address=" + order.getUserAddress()
                        + "&phone=" + order.getUserPhone()
                        + "&page=";
                request.getSession().setAttribute(LAST_COMMAND, lastCommand + page.getCurrentPage());
                request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE, lastCommand);

                request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/orderInfo.jsp")
                        .forward(request, response);
            }
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
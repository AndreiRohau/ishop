package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ProductInfoCommand implements Command {

    private static final Logger logger = Logger.getLogger(ProductInfoCommand.class);
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ProductInfoCommand");
        try {
            String goToPage;

            Product product  = new Product();
            product.setId(Integer.parseInt(request.getParameter("productId")));
            product = productService.findProductWithId(product);

            if(product != null){
                request.setAttribute("product", product);
                goToPage = "/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/productInfo.jsp";
                request.getSession().setAttribute(LAST_COMMAND.inString,
                        "FrontController?command=productInfo&productId=" + request.getParameter("productId"));
            } else {
                goToPage = String.valueOf(request.getSession().getAttribute(LAST_COMMAND.inString));
                request.setAttribute("cant_find_product", true);
            }
            request.getRequestDispatcher(goToPage).forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

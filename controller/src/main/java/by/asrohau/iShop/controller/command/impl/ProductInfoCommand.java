package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ProductInfoCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ProductInfoCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ProductInfoCommand");
        try {
            Product product  = new Product();
            product.setId(Integer.parseInt(request.getParameter(ID)));
            product = productService.findProductWithId(product);

            if(product != null){
                request.setAttribute("product", product);
                request.getSession().setAttribute(LAST_COMMAND,
                        "FrontController?command=productInfo&id=" + request.getParameter(ID));
            } else {
                request.setAttribute("cannotFindProduct", true);
            }

            request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/productInfo.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

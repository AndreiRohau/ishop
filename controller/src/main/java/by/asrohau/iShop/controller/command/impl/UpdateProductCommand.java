package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class UpdateProductCommand implements Command {

    private static final Logger logger = Logger.getLogger(UpdateProductCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to EditProductCommand");
        try {
            Product product = new Product(Integer.parseInt(request.getParameter("id")),
                    request.getParameter("company"),
                    request.getParameter("name"),
                    request.getParameter("type"),
                    request.getParameter("price"),
                    request.getParameter("description"));

            if(!productService.updateProduct(product)){
                request.setAttribute("updateFailed", true);
                product = productService.findProductWithId(product);
            }

            request.setAttribute("product", product);
            request.getSession().setAttribute(LAST_COMMAND, "FrontController?command=productInfo&id=" + request.getParameter(ID));
            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE) + "/productInfo.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
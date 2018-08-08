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

public class DeleteProductCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteProductCommand.class);
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to delete PRODUCT Command");
        try {
            String lastCommand = "";
            Product product = new Product();
            product.setId(Integer.parseInt(request.getParameter(ID.inString)));

            if (productService.deleteProduct(product)) {
                lastCommand = "FrontController?command=goToPage&address=main.jsp";
            } else {
                lastCommand = "FrontController?command=productInfo&productId=" + product.getId();
                request.setAttribute("updateFailed", true);
            }
            request.getSession().setAttribute(LAST_COMMAND.inString, lastCommand);
            response.sendRedirect(lastCommand);
        } catch (ServiceException | IOException e) { // ServletException |
            throw new ControllerException(e);
        }

    }

}

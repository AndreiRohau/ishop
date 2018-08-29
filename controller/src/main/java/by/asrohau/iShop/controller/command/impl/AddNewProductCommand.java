package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;
import static by.asrohau.iShop.controller.ControllerFinals.MESSAGE;

public class AddNewProductCommand extends AbstractCommand {
    private static final Logger logger = LoggerFactory.getLogger(AddNewProductCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to AddProductCommand");
        try {
            Product newProduct = new Product(request.getParameter("company").trim(),
                    request.getParameter("name").trim(),
                    request.getParameter("type").trim(),
                    request.getParameter("price").trim(),
                    request.getParameter("description").trim());

            boolean productAdded = productService.addNewProduct(newProduct);

            String lastCommand = request.getSession().getAttribute(LAST_COMMAND) +
                    "&" + MESSAGE + "=" + productAdded;
            request.getSession().setAttribute(LAST_COMMAND, lastCommand);
            response.sendRedirect(lastCommand);
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

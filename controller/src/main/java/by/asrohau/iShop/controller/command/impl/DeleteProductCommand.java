package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class DeleteProductCommand extends AbstractCommand {
    private static final Logger logger = LoggerFactory.getLogger(DeleteProductCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to delete PRODUCT Command");
        try {
            String lastCommand = "";
            long productId = Long.parseLong(request.getParameter(ID));

            if (productService.deleteProduct(productId)) {
                lastCommand = "FrontController?command=goToPage&address=main.jsp";
            } else {
                lastCommand = request.getSession().getAttribute(LAST_COMMAND) + "&" + MESSAGE + "=" + false;
            }

            request.getSession().setAttribute(LAST_COMMAND, lastCommand);
            response.sendRedirect(lastCommand);
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }

}

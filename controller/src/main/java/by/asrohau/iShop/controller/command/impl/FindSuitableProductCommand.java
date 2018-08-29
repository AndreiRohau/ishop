package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.Page;
import by.asrohau.iShop.entity.Product;
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

public class FindSuitableProductCommand extends AbstractCommand {

    private static final Logger logger = LoggerFactory.getLogger(FindSuitableProductCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to FindSuitableProductCommand");
        try {
            Product product = new Product(
                    request.getParameter("company").trim(),
                    request.getParameter("name").trim(),
                    request.getParameter("type").trim(),
                    request.getParameter("price").trim());

            Page page = new Page(request.getParameter(PAGE), productService.countProductsLike(product));
            List<Product> products = productService.getProductsLike(product, page.getRow());

            request.setAttribute("products", products);
            request.setAttribute("page", page);

            String lastCommand = defineCommand(request, false);
            request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE, lastCommand);
            request.getSession().setAttribute(LAST_COMMAND, lastCommand + page.getCurrentPage());

            request.getRequestDispatcher("/WEB-INF/jsp/" + request.getSession().getAttribute(ROLE) + "/main.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

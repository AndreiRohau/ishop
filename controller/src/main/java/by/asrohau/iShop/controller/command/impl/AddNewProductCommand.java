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

import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;

public class AddNewProductCommand implements Command {
    private static final Logger logger = Logger.getLogger(AddNewProductCommand.class);
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

            if (productService.addNewProduct(newProduct)) {
                request.setAttribute("isAdded", true);
            } else {
                request.setAttribute("isAdded", false);
            }

            request.getSession().setAttribute(LAST_COMMAND, "FrontController?command=goToPage&address=addProduct.jsp");
            request.getRequestDispatcher("/jsp/admin/addProduct.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

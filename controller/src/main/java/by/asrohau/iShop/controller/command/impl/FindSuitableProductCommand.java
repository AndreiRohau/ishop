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
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class FindSuitableProductCommand implements Command {

    private static final Logger logger = Logger.getLogger(FindSuitableProductCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to FindSuitableProductCommand");

        String company = request.getParameter("company").trim();
        String name = request.getParameter("name").trim();
        String type = request.getParameter("type").trim();
        String price = request.getParameter("price").trim();

        Product product = new Product();
        if (!"".equals(company)) {
            product.setCompany(company);
        }
        if (!"".equals(name)) {
            product.setName(name);
        }
        if (!"".equals(type)) {
            product.setType(type);
        }
        if (!"".equals(price)) {
            product.setPrice(price);
        }

        try {
            int currentPage = Integer.parseInt(request.getParameter(PAGE.inString));
            int maxPage = (int) Math.ceil(((double) productService.countProductsComprehensive(product)) / Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            int row = (currentPage - 1) * Integer.parseInt(MAX_ROWS_AT_PAGE.inString);

            List<Product> products = productService.findProductsComprehensive(product, row); //ArrayList
            request.setAttribute("products", products);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            String path = "FrontController?command=findSuitableProduct"
                    + "&company=" + company
                    + "&name=" + name
                    + "&type=" + type
                    + "&price=" + price
                    + "&page=";
            request.getSession().setAttribute(LAST_COMMAND_PAGE.inString, path);
            request.getSession().setAttribute(LAST_COMMAND.inString, path + currentPage);

            request.getRequestDispatcher("/jsp/" + request.getSession().getAttribute(ROLE.inString) + "/main.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.controller.ControllerFinals;
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

import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class FindSuitableCommand implements Command {

    private static final Logger logger = Logger.getLogger(FindSuitableCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to FindSuitableCommand");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();

        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)* Integer.parseInt(MAX_ROWS_AT_PAGE.inString);

        String check = "";
        String company = request.getParameter("company").trim();
        String name = request.getParameter("name").trim();
        String type = request.getParameter("type").trim();
        String price = request.getParameter("price").trim();

        Product product = new Product();
        if (!company.equals(check)) {
            product.setCompany(company);
        }
        if (!name.equals(check)) {
            product.setName(name);
        }
        if (!type.equals(check)) {
            product.setType(type);
        }
        if (!price.equals(check)) {
            product.setPrice(price);
        }

        try {
            //count amount of all products
            maxPage = (int) Math.ceil(((double) productService.countProductsComprehensive(product)) / 15);

            List<Product> productArrayList = productService.findProductsComprehensive(product, row); //ArrayList
            request.setAttribute("productArray", productArrayList);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            String path = "FrontController?command=findSuitable"
                    + "&company=" + company
                    + "&name=" + name
                    + "&type=" + type
                    + "&price=" + price
                    + "&page_num=";
            request.getSession().setAttribute("lastCMDneedPage", path);
            request.getSession().setAttribute("lastCMD", path + currentPage);

            if(!request.getSession().getAttribute(ROLE.inString).equals(ADMIN.inString)){
                goToPage = "/jsp/user/main.jsp";
            } else {
                goToPage = "/jsp/admin/manageProducts.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class FindSuitableCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to FindSuitableCommand");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();

        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)*15;

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

            ArrayList<Product> productArrayList = productService.findProductsComprehensive(product, row);
            request.setAttribute("productArray", productArrayList);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=findSuitable"
                            + "&company=" + company
                            + "&name=" + name
                            + "&type=" + type
                            + "&price=" + price
                            + "&page_num=" + currentPage);
            request.getSession().setAttribute("lastCMDneedPage",
                    "FrontController?command=findSuitable"
                            + "&company=" + company
                            + "&name=" + name
                            + "&type=" + type
                            + "&price=" + price
                            + "&page_num=");

            if(!request.getSession().getAttribute("userName").equals("Admin")){
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

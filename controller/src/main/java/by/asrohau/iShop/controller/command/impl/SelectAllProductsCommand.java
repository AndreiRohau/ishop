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
import java.util.List;

public class SelectAllProductsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to SelectAllProductsCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();
        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)*15;

        try {
            //count amount of all products
            maxPage = (int) Math.ceil(((double) productService.countProducts()) / 15);

            List<Product> productArrayList = productService.getAllProducts(row); // ArrayList
            request.setAttribute("productArray", productArrayList);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=selectAllProducts&page_num=" + currentPage);
            request.getSession().setAttribute("lastCMDneedPage",
                    "FrontController?command=selectAllProducts&page_num=");

            if(!request.getSession().getAttribute("userName").equals("Admin")){
                goToPage = "/jsp/user/main.jsp";
            } else {
                goToPage = "/jsp/admin/manageProducts.jsp";
            }
            //what if not null??
            request.setAttribute("msg", request.getParameter("msg"));
            // what if not null ??
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

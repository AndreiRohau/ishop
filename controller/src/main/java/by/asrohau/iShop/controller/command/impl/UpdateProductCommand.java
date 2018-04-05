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

public class UpdateProductCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to EditProductCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();
        String lastCMD;
        String goToPage;
        Product product = new Product(Integer.parseInt(request.getParameter("id")),
                request.getParameter("company"),
                request.getParameter("name"),
                request.getParameter("type"),
                request.getParameter("price"),
                request.getParameter("description"));

        try {
            if(!productService.updateProduct(product)){
                request.setAttribute("updateFailed", "Update failed");
                product = productService.findProductWithId(product);
            }
            request.setAttribute("productToEdit", product);
            lastCMD = "FrontController?command=editProduct&productId=" + request.getParameter("id");
            goToPage = "/jsp/admin/editProduct.jsp";

            request.getSession().setAttribute("lastCMD", lastCMD);
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
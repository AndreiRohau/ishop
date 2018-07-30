package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.controller.ControllerFinals;
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

public class AddNewProductCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to AddProductCommand");


        boolean isAdded; //= false
        Product newProduct = new Product(request.getParameter("company").trim(),
                request.getParameter("inString").trim(),
                request.getParameter("type").trim(),
                request.getParameter("price").trim(),
                request.getParameter("description").trim());

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();

        request.getSession().setAttribute("lastCMD", "FrontController?command=goToPage&address=manageProducts.jsp");
        request.setAttribute("isAdded", null);
        try {
            /*
            //Check accessability if there's no proper filter
            if(request.getSession().getAttribute(ControllerFinals.ROLE.inString) == null){
                response.sendRedirect(ControllerFinals.INDEX.inString);
            }
            */
            isAdded = productService.addNewProduct(newProduct);
            String goToPage;
            if (isAdded) {
                goToPage = "/jsp/admin/manageProducts.jsp";
                request.setAttribute("isAdded", "You have added new product successfully");
            } else {
                goToPage = "error.jsp";
                request.setAttribute("errorMessage", "Can NOT add this product, try again!");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

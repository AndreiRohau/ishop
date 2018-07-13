package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectAllReservedCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to FindReservedProductsCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();
        ProductService productService = serviceFactory.getProductService();

        User user = new User();
        Product product = new Product();

        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)*15;

        try {
            user.setLogin((String) request.getSession().getAttribute("userName"));
            int user_id = userService.findIdWithLogin(user).getId();

            //count amount of all products
            maxPage = (int) Math.ceil(((double) orderService.countReserved(user_id)) / 15);

            List<Product> reservedWithIdsList = orderService.getAllReserved(user_id, row); //product_id & reserve_id // ArrayList

            ArrayList<Product> productArray = new ArrayList<>();

            for(Product prod : reservedWithIdsList){
                product = productService.findProductWithId(prod);
                product.setReserveId(prod.getReserveId());
                productArray.add(product);
                product = new Product();
            }

            request.setAttribute("productArray", productArray);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=selectAllReserved&page_num=" + currentPage);

            goToPage = "/jsp/user/basket.jsp";

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

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

//get user ID
//get product list
//delete reserved products from this USER
//(add to Reserve Table) convert product list into ( id(key) ---- user_id ---- product_id %,% ---- address ---- phone)
//доделать - удаление юзера
//доделать - удаление его корзины
//доделать - удаление его заказа


public class CreateOrderCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to CreateOrderCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();

        String goToPage;
        String productIDs = null;
        String message = null;
        String user_address;
        String user_phone;
        User user = new User();
        user.setLogin((String) request.getSession().getAttribute("userName"));


    try{
        //got user ID
        int user_id = userService.findIdWithLogin(user).getId();
        //got list of product IDs
        LinkedList<Integer> products = orderService.getAllReservedIds(user_id);
        //transform list into string of IDs divided with ,
        for(int id : products){
            if(productIDs != null) {
                productIDs = productIDs + String.valueOf(id) + ",";
            } else {
                productIDs = String.valueOf(id) + ",";
            }

        }
        //get user_address
        user_address = request.getParameter("user_address");
        //get user_phone
        user_phone = request.getParameter("user_phone");

        //creating order obj
        Order order = new Order(user_id, productIDs, user_address, user_phone, "new");
        //save into orders TABLE
        boolean orderIsSaved = orderService.saveNewOrder(order);

        if(orderIsSaved) {
            //delete reserved
            boolean reservedIsDeleted = orderService.deleteAllReserved(user_id);
            goToPage = "/jsp/user/main.jsp";
            message = "Order created - wait for the delivery.";
        } else {
            goToPage = "error.jsp";
            request.setAttribute("errorMessage", "Error while creating the order.");
        }

        //what if not null??
        request.setAttribute("msg", message);
        // what if not null ??
        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);

    } catch (ServiceException | ServletException | IOException e) {
        throw new ControllerException(e);
    }
    }
}

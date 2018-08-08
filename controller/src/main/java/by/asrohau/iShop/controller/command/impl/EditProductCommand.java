//package by.asrohau.iShop.controller.command.impl;
//
//import by.asrohau.iShop.bean.Product;
//import by.asrohau.iShop.controller.command.Command;
//import by.asrohau.iShop.controller.exception.ControllerException;
//import by.asrohau.iShop.service.ProductService;
//import by.asrohau.iShop.service.ServiceFactory;
//import by.asrohau.iShop.service.exception.ServiceException;
//import org.apache.log4j.Logger;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class EditProductCommand implements Command {
//    private static final Logger logger = Logger.getLogger(EditProductCommand.class);
//
//    ServiceFactory serviceFactory = ServiceFactory.getInstance();
//    ProductService productService = serviceFactory.getProductService();
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
//        logger.info("We got to EditProductCommand");
//        try {
//            String lastCMD;
//            String goToPage;
//            Product product  = new Product();
//            product.setId(Integer.parseInt(request.getParameter("productId")));
//            product = productService.findProductWithId(product);
//
//            if(product != null){
//                request.setAttribute("product", product);
//                goToPage = "/jsp/admin/productInfo.jsp";
//                lastCMD = "FrontController?command=editProduct&productId=" + request.getParameter("productId");
//
//            } else {
//                goToPage = "error.jsp";
//                lastCMD = "FrontController?command=goToPage&address=manageProducts.jsp";
//            }
//            request.getSession().setAttribute("lastCMD", lastCMD);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
//            dispatcher.forward(request, response);
//
//        } catch (ServiceException | ServletException | IOException e) {
//            throw new ControllerException(e);
//        }
//    }
//}

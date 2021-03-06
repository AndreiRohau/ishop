package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.Page;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ShowReservedCommand extends AbstractCommand {

    private static final Logger logger = LoggerFactory.getLogger(ShowReservedCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ReserveService reserveService= serviceFactory.getReserveService();
    private ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to ShowReservedCommand");
        try {
            long userId = (Long) request.getSession().getAttribute(ID);
            Page page = new Page(request.getParameter(PAGE), reserveService.countReserved(userId));
            List<Reserve> reservations = reserveService.getReservations(userId, page.getRow());
            List<Product> products = productService.findProductsWithIds(reservations);

            request.setAttribute("products", products);
            request.setAttribute("page", page);
            request.setAttribute(MESSAGE, request.getParameter(MESSAGE));
            String lastCommand = defineCommand(request, false);
            request.getSession().setAttribute(LAST_COMMAND, lastCommand + page.getCurrentPage());
            request.getSession().setAttribute(LAST_COMMAND_NEED_PAGE, lastCommand);

            request.getRequestDispatcher("/WEB-INF/jsp/user/basket.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class DeleteReserveCommand implements Command {

    private static final Logger logger = Logger.getLogger(DeleteReserveCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to DeleteReserveCommand");
        try {
            boolean deleted = orderService.deleteReserved(Integer.parseInt(request.getParameter("reserveId")));

            response.sendRedirect(String.valueOf(request.getSession().getAttribute(LAST_COMMAND.inString))
                    + "&deleted=" + deleted);

        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

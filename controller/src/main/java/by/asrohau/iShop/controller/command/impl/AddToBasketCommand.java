package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class AddToBasketCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(AddToBasketCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ReserveService reserveService= serviceFactory.getReserveService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to AddToBasketCommand");
        try {
            Reserve reserve = new Reserve((Long) request.getSession().getAttribute(ID), Long.parseLong(request.getParameter(ID)));
            boolean productAdded = reserveService.saveReserve(reserve);

            response.sendRedirect(String.valueOf(request.getSession().getAttribute(LAST_COMMAND))
                    + "&success=" + productAdded);
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;

public class DeleteReserveCommand extends AbstractCommand {

    private static final Logger logger = LoggerFactory.getLogger(DeleteReserveCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ReserveService reserveService= serviceFactory.getReserveService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to DeleteReserveCommand");
        try {
            boolean deleted = reserveService.deleteReserved(Long.parseLong(request.getParameter("reserveId")));

            response.sendRedirect((String) request.getSession().getAttribute(LAST_COMMAND));
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

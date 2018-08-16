package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;

public class DeleteReserveCommand implements Command {

    private static final Logger logger = Logger.getLogger(DeleteReserveCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ReserveService reserveService= serviceFactory.getReserveService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        logger.info("We got to DeleteReserveCommand");
        try {
            boolean deleted = reserveService
                    .deleteReserved(new Reserve(Long.parseLong(request.getParameter("reserveId"))));

            response.sendRedirect(String.valueOf(request.getSession().getAttribute(LAST_COMMAND))
                    + "&deleted=" + deleted);

        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}

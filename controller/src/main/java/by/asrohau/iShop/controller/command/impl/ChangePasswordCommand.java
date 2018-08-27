package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ChangePasswordCommand extends AbstractCommand {

	private static final Logger logger = LoggerFactory.getLogger(ChangePasswordCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info("We got to ChangePasswordCommand");
		try {
			User user = new User(request.getParameter(LOGIN).trim(), request.getParameter(PASSWORD).trim());
			String newPassword = request.getParameter(NEW_PASSWORD).trim();
			String sessionLogin = (String) request.getSession().getAttribute(LOGIN);

			boolean passwordChanged = userService.changePassword(user, newPassword, sessionLogin);

			String lastCommand = request.getSession().getAttribute(LAST_COMMAND) +
					"&" + MESSAGE + "=" + passwordChanged;
			request.getSession().setAttribute(LAST_COMMAND, lastCommand);
			response.sendRedirect(lastCommand);
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

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

public class RegistrationCommand extends AbstractCommand {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info("We got to RegistrationCommand");
		try {
			User user = new User(request.getParameter(LOGIN).trim(),
					request.getParameter(PASSWORD).trim(),
					String.valueOf(request.getSession().getAttribute(ROLE)));
			String password2 = request.getParameter(PASSWORD_2).trim();

			String lastCommand = "FrontController?command=goToPage&address=index.jsp";
			if (userService.registration(user, password2)) {
				lastCommand = lastCommand + "&message=true";
			} else {
				String message = password2.equals(user.getPassword()) ? "exists" : "passwordsUnequal";
				message = request.getSession().getAttribute(ROLE) == null ? message : "logOutFirst";
				lastCommand = lastCommand + "&message=" + message;
			}

			response.sendRedirect(lastCommand);
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

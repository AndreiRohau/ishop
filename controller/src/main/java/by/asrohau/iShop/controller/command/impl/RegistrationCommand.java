package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class RegistrationCommand implements Command {

	private final static Logger logger = LoggerFactory.getLogger(RegistrationCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(REGISTRATION_COMMAND);
		try {
			User user = new User(request.getParameter(LOGIN).trim(),
					request.getParameter(PASSWORD).trim(),
					USER);
			boolean isRegistered = false;
			boolean passwordEquals = request.getParameter("password2").trim().equals(user.getPassword());

			if(passwordEquals && request.getSession().getAttribute(ROLE) == null) {
				isRegistered = userService.registration(user);
			}

			if (isRegistered) {
				request.setAttribute("isRegistered", true);
			} else {
				String errorMessage = passwordEquals ? "exists" : "passwordsUnequal";
				errorMessage = request.getSession().getAttribute(ROLE) == null ? errorMessage : "logOutFirst";
				request.setAttribute(ERROR_MESSAGE, errorMessage);
			}
			request.getSession().setAttribute(LAST_COMMAND, INDEX);
			request.getRequestDispatcher(INDEX).forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

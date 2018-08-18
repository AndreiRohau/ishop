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

			if (userService.registration(user, password2)) {
				request.setAttribute("isRegistered", true);
			} else {
				String errorMessage = password2.trim().equals(user.getPassword()) ? "exists" : "passwordsUnequal";
				errorMessage = request.getSession().getAttribute(ROLE) == null ? errorMessage : "logOutFirst";
				request.setAttribute(ERROR_MESSAGE, errorMessage);
			}

			request.getSession().setAttribute(LAST_COMMAND, "index.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

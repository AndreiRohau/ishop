package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class RegistrationCommand implements Command {

	private final static Logger logger = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(REGISTRATION_COMMAND.inString);
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			User user = new User(request.getParameter(LOGIN.inString).trim(),
					request.getParameter(PASSWORD.inString).trim(),
					USER.inString);
			boolean isRegistered = false;
			boolean passwordEquals = request.getParameter("password2").trim().equals(user.getPassword());

			if(passwordEquals && request.getSession().getAttribute(ROLE.inString) == null) {
				isRegistered = userService.registration(user);
			}

			if (isRegistered) {
				request.setAttribute("isRegistered", true);
			} else {
				String errorMessage = passwordEquals ? "exists" : "passwordsUnequal";
				errorMessage = request.getSession().getAttribute(ROLE.inString) == null ? errorMessage : "logout";
				request.setAttribute(ERROR_MESSAGE.inString, errorMessage);
			}
			request.getSession().setAttribute(LAST_COMMAND.inString, INDEX.inString);
			request.getRequestDispatcher(INDEX.inString).forward(request, response);
			
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

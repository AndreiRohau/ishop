package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ChangePasswordCommand implements Command {

	private static final Logger logger = Logger.getLogger(ChangePasswordCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(CHANGE_PASSWORD_COMMAND.inString);
		try {
			User user = new User(request.getParameter(LOGIN.inString).trim(), request.getParameter(PASSWORD.inString).trim());
			String newPassword = request.getParameter(NEW_PASSWORD.inString).trim();

			boolean isChanged = request.getSession().getAttribute(LOGIN.inString).equals(user.getLogin())
					&& userService.changePassword(user, newPassword);
			if (isChanged) {
				request.setAttribute(IS_CHANGED.inString, newPassword);
			} else {
				request.setAttribute(ERROR_MESSAGE.inString, "changePasswordError");
			}

			request.getSession().setAttribute(LAST_COMMAND.inString, "FrontController?command=goToPage&address=profile.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/profile.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

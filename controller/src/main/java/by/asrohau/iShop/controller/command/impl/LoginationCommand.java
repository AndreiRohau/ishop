package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
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

public class LoginationCommand implements Command {

	private final static Logger logger = Logger.getLogger(LoginationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(LOGINATION_COMMAND.inString);

		User user = new User(request.getParameter(LOGIN.inString).trim(),
				request.getParameter(PASSWORD.inString).trim());
		UserDTO userDTO;
		String lastCMD;
		String goToPage;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		String errorMessage = "No such User";//todo

		try {
			UserService userService = serviceFactory.getUserService();
			userDTO = userService.logination(user);

			if (userDTO != null) {
				goToPage = "/jsp/" + userDTO.getRole() + "/main.jsp";
				lastCMD = GO_TO_PAGE_MAIN.inString;
				request.getSession().setAttribute(ROLE.inString, userDTO.getRole());
				request.getSession().setAttribute(USER_NAME.inString, userDTO.getLogin());
			} else {
				goToPage = "error.jsp";
				lastCMD = GO_TO_PAGE_INDEX.inString;
			}

			request.getSession(true).setAttribute(LAST_COMMAND.inString, lastCMD);

			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}

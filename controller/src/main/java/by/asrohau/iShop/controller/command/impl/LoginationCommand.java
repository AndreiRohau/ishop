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
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(LOGINATION_COMMAND.inString);
		try {
			User user = new User(request.getParameter(LOGIN.inString).trim(),
					request.getParameter(PASSWORD.inString).trim());
			UserDTO userDTO = userService.logination(user);

			String lastCMD;
			String goToPage;

			if (userDTO == null) {
				goToPage = INDEX.inString;
				lastCMD = GO_TO_PAGE_INDEX.inString;
				request.setAttribute(ERROR_MESSAGE.inString, NO_SUCH_USER.inString);
			} else {
				goToPage = "/jsp/" + userDTO.getRole() + "/main.jsp";
				lastCMD = GO_TO_PAGE_MAIN.inString;
				request.getSession(true).setAttribute(ROLE.inString, userDTO.getRole());
				request.getSession().setAttribute(LOGIN.inString, userDTO.getLogin());
			}

			request.getSession(true).setAttribute(LAST_COMMAND.inString, lastCMD);
			request.getRequestDispatcher(goToPage).forward(request, response);
		} catch (ServiceException | ServletException | IOException e) { //
			throw new ControllerException(e);
		}

	}

}

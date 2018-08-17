package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
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

public class LoginationCommand implements Command {

	private final static Logger logger = LoggerFactory.getLogger(LoginationCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(LOGINATION_COMMAND);
		try {
			User user = new User(request.getParameter(LOGIN).trim(),
					request.getParameter(PASSWORD).trim());
			UserDTO userDTO = userService.logination(user);

			String lastCMD;
			String goToPage;

			if (userDTO == null) {
				goToPage = INDEX;
				lastCMD = GO_TO_PAGE_INDEX;
				request.setAttribute(ERROR_MESSAGE, NO_SUCH_USER);
			} else {
				goToPage = "/WEB-INF/jsp/" + userDTO.getRole() + "/main.jsp";
				lastCMD = GO_TO_PAGE_MAIN;
				request.getSession(true).setAttribute(ID, userDTO.getId());
				request.getSession().setAttribute(ROLE, userDTO.getRole());
				request.getSession().setAttribute(LOGIN, userDTO.getLogin());

			}

			request.getSession(true).setAttribute(LAST_COMMAND, lastCMD);
			request.getRequestDispatcher(goToPage).forward(request, response);
		} catch (ServiceException | ServletException | IOException e) { //
			throw new ControllerException(e);
		}

	}

}

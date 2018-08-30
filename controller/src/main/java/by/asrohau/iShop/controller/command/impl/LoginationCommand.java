package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class LoginationCommand extends AbstractCommand {

	private static final Logger logger = LoggerFactory.getLogger(LoginationCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info("We got to LoginationCommand");
		try {
			User user = new User(request.getParameter(LOGIN).trim(),
					request.getParameter(PASSWORD).trim());

			UserDTO userDTO = userService.logination(user);

			String lastCommand;
			if (userDTO == null) {
				lastCommand = "FrontController?command=goToPage&address=index.jsp&message=noSuchUser";
			} else {
				lastCommand = "FrontController?command=goToPage&address=main.jsp";
				request.getSession(true).setAttribute(ID, userDTO.getId());
				request.getSession().setAttribute(ROLE, userDTO.getRole());
				request.getSession().setAttribute(LOGIN, userDTO.getLogin());
			}

			response.sendRedirect(lastCommand);
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}

	}

}

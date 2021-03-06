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

public class DeleteUserCommand extends AbstractCommand {

	private static final Logger logger = LoggerFactory.getLogger(DeleteUserCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info("We got to DeleteUserCommand");
		try {
			String lastCommand;
			boolean isUser = "user".equals(request.getSession().getAttribute(ROLE));
			User user = new User(
					Long.parseLong(request.getParameter(ID)),
					request.getParameter(LOGIN).trim(),
					request.getParameter(PASSWORD).trim());

			boolean isDeleted = userService.deleteUser(user, isUser);

			if(isDeleted && isUser){
				request.getSession().invalidate();
				lastCommand = "FrontController?command=goToPage&address=index.jsp";
			} else if (isDeleted) {
				lastCommand = "FrontController?command=showUsers&page=1";
			} else {
				lastCommand = request.getSession().getAttribute(LAST_COMMAND) + "&message=deleteUserError";
			}

			response.sendRedirect(lastCommand);
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}

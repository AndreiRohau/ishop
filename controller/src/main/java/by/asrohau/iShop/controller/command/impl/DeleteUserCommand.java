package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ReserveService;
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

public class DeleteUserCommand implements Command {

	private static final Logger logger = LoggerFactory.getLogger(DeleteUserCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	private ReserveService reserveService= serviceFactory.getReserveService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(DELETE_USER_COMMAND);
		try {
			User user = new User(request.getParameter(LOGIN).trim(),
								request.getParameter(PASSWORD).trim());
			UserDTO userDTO = userService.findUserDTOWithLogin(user);
			String goToPage;
			String lastCMD;
			boolean isDeleted = false;

			String userLogin = ((String) request.getSession().getAttribute(LOGIN));
			boolean actualUser = userLogin.equals(user.getLogin());
			boolean isAdmin = request.getSession().getAttribute(ROLE).equals(ADMIN);


			if (!isAdmin) {
				goToPage = "/WEB-INF/jsp/user/profile.jsp";
				lastCMD = GO_TO_PAGE_PROFILE;
				request.setAttribute(ERROR_MESSAGE, "deleteUserError");
			} else {
				goToPage = "FrontController?command=userInfo&id=" + userDTO.getId();
				lastCMD = "FrontController?command=userInfo&id=" + userDTO.getId();
				request.setAttribute(ERROR_MESSAGE, "deleteUserError");
			}

			if (actualUser) {
				userDTO.setId(userService.findUserDTOWithLogin(user).getId());
				isDeleted = userService.deleteUser(user);
				request.setAttribute(ERROR_MESSAGE, "deleteUserError");
				goToPage = "/WEB-INF/jsp/user/profile.jsp";
				lastCMD = GO_TO_PAGE_PROFILE;
			}
			if (isDeleted && actualUser) {
				reserveService.deleteAllReserved(userDTO.getId());
				request.getSession().invalidate();
				goToPage = INDEX;
				lastCMD = GO_TO_PAGE_INDEX;
			}
			if (isAdmin) {
				userDTO.setId(userService.findUserDTOWithLogin(user).getId());
				isDeleted = userService.deleteUser(user);
			}
			if (isDeleted && isAdmin) {
				goToPage = "FrontController?command=showUsers&page=1";
				lastCMD = "FrontController?command=showUsers&page=1";
				reserveService.deleteAllReserved(userDTO.getId());
				request.removeAttribute(ERROR_MESSAGE);
			}

			request.getSession().setAttribute(LAST_COMMAND, lastCMD);
			request.getRequestDispatcher(goToPage).forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}

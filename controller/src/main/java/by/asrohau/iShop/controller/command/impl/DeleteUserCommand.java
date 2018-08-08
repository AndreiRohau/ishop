package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.OrderService;
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

public class DeleteUserCommand implements Command {

	private static final Logger logger = Logger.getLogger(DeleteUserCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	private OrderService orderService = serviceFactory.getOrderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(DELETE_USER_COMMAND.inString);
		try {
			User user = new User(request.getParameter(LOGIN.inString).trim(),
								request.getParameter(PASSWORD.inString).trim());
			User userDTO = new User();
			String goToPage;
			String lastCMD;
			boolean isDeleted = false;

			String userLogin = ((String) request.getSession().getAttribute(LOGIN.inString));
			boolean actualUser = userLogin.equals(user.getLogin());
			boolean isAdmin = request.getSession().getAttribute(ROLE.inString).equals(ADMIN.inString);


			if (!isAdmin) {
				goToPage = "/jsp/user/profile.jsp";
				lastCMD = GO_TO_PAGE_PROFILE.inString;
				request.setAttribute(ERROR_MESSAGE.inString, "deleteUserError");
			} else {
				goToPage = "/jsp/admin/users.jsp";
				lastCMD = "FrontController?command=goToPage&address=users.jsp";
			}

			if (actualUser) {
				userDTO.setId(userService.findUserDTOWithLogin(user).getId());
				isDeleted = userService.deleteUser(user);
				request.setAttribute(ERROR_MESSAGE.inString, "deleteUserError");
				goToPage = "/jsp/user/profile.jsp";
				lastCMD = GO_TO_PAGE_PROFILE.inString;
			}
			if (isDeleted && actualUser) {
				orderService.deleteAllReserved(userDTO.getId());
				request.getSession().invalidate();
				goToPage = INDEX.inString;
				lastCMD = GO_TO_PAGE_INDEX.inString;
			}
			if (isAdmin) {
				userDTO.setId(userService.findUserDTOWithLogin(user).getId());
				isDeleted = userService.deleteUser(user);
				request.setAttribute(ERROR_MESSAGE.inString, "deleteUserError");
				goToPage = "/jsp/admin/users.jsp";
				lastCMD = "FrontController?command=goToPage&address=users.jsp";
			}
			if (isDeleted && isAdmin) {
				orderService.deleteAllReserved(userDTO.getId());
				request.removeAttribute(ERROR_MESSAGE.inString);
			}

			request.getSession().setAttribute(LAST_COMMAND.inString, lastCMD);
			request.getRequestDispatcher(goToPage).forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}

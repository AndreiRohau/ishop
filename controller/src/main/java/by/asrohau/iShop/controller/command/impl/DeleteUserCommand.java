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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(DELETE_USER_COMMAND.inString);
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			OrderService orderService = serviceFactory.getOrderService();

			User user = new User(request.getParameter(LOGIN.inString).trim(),
					request.getParameter(PASSWORD.inString).trim());
			User userDTO = new User();

			boolean deleted = false;
			String l = ((String) request.getSession().getAttribute(USER_NAME.inString));
			logger.info(l);
			logger.info(user.getLogin());
			boolean isUser = l.equals(user.getLogin());
			boolean isAdmin = request.getSession().getAttribute(ROLE.inString).equals(ADMIN.inString);

			String goToPage;
			String lastCMD;
			if (!isAdmin) {
				goToPage = "/jsp/user/profile.jsp";
				lastCMD = GO_TO_PAGE_PROFILE.inString;
				request.setAttribute(ERROR_MESSAGE.inString, "delete_user_error");
			} else {
				goToPage = "/jsp/admin/manageClients.jsp";
				lastCMD = "FrontController?command=goToPage&address=manageClients.jsp";
			}

			if (isUser) {
				userDTO.setId(userService.findIdWithLogin(user).getId());
				deleted = userService.deleteUser(user);
				request.setAttribute(ERROR_MESSAGE.inString, "delete_user_error");
				goToPage = "/jsp/user/profile.jsp";
				lastCMD = GO_TO_PAGE_PROFILE.inString;
			}
			if (deleted && isUser) {
				orderService.deleteAllReserved(userDTO.getId());
				request.getSession().invalidate();
				goToPage = INDEX.inString;
				lastCMD = GO_TO_PAGE_INDEX.inString;
			}
			if (isAdmin) {
				userDTO.setId(userService.findIdWithLogin(user).getId());
				deleted = userService.deleteUser(user);
				request.setAttribute(ERROR_MESSAGE.inString, "delete_user_error");
				goToPage = "/jsp/admin/manageClients.jsp";
				lastCMD = "FrontController?command=goToPage&address=manageClients.jsp";
			}
			if (deleted && isAdmin) {
				orderService.deleteAllReserved(userDTO.getId());
				request.setAttribute(ERROR_MESSAGE.inString, null);
			}

			request.getSession().setAttribute("lastCMD", lastCMD);
			request.getRequestDispatcher(goToPage).forward(request, response);
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}

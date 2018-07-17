package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import by.asrohau.iShop.service.ServiceFactory;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		System.out.println("We got to ChangePasswordCommand");

		User user = new User(request.getParameter("login").trim(),
				request.getParameter("password").trim());
		String newPassword = request.getParameter("newPassword").trim();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		request.getSession().setAttribute("lastCMD", "FrontController?command=goToPage&address=profile.jsp");
		boolean isChanged; //was false

		try {
			isChanged = request.getSession().getAttribute("userName").equals(user.getLogin()) && userService.changePassword(user, newPassword);
			String goToPage;
			if (isChanged) {
				request.setAttribute("isChanged", "new password is: " + newPassword);
				goToPage = "/jsp/user/profile.jsp"; //was just address
			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "Can NOT change password.");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);

		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

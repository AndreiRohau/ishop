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

public class RegistrationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		System.out.println("We got to REGISTRATION");
		boolean isRegistered;
		User user = new User(request.getParameter("login").trim(), request.getParameter("password").trim());
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		String lastCMD = "FrontController?command=goToPage&address=index.jsp";
		String goToPage;
		try {
			isRegistered = request.getSession().getAttribute("userName") == null
					&& !request.getParameter("login").equals("Admin")
					&& userService.registration(user);

			if (isRegistered) {
				request.setAttribute("isRegistered", "You registered successfully");
				goToPage = "index.jsp";
			} else {
				goToPage = "error.jsp";
				String message = request.getSession().getAttribute("userName") == null
						? "Login exists" : "Log out!";
				request.setAttribute("errorMessage", message);
			}
			request.getSession().setAttribute("lastCMD", lastCMD);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
			
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}

}

package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		System.out.println("We got to ChangeLanguageCommand");
		try {

			request.getSession(true).setAttribute("local", request.getParameter("local"));

			if (request.getSession(true).getAttribute("lastCMD") != null){
				response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD")));
			}else{
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}
}

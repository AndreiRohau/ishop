package by.asrohau.iShop.controller;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.command.CommandFactory;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public final class FrontController extends HttpServlet {
	private final static Logger logger = Logger.getLogger(FrontController.class);
	private static final long serialVersionUID = 1L;
	private final Map<String, Command> commandMap = CommandFactory.getInstance().getCommandMap();

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	private void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("START servlet : " + request.getMethod() + " : command : " + request.getParameter("command"));
		try {
			Command command = (Command) commandMap.get(request.getParameter(COMMAND.inString));
			command.execute(request, response);
		} catch (ControllerException e) {
			request.setAttribute(ERROR_MESSAGE.inString, e.toString());
			request.getRequestDispatcher(ERROR.inString).forward(request, response);
		}
	}
}

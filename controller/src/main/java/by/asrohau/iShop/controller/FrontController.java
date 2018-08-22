package by.asrohau.iShop.controller;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.command.CommandFactory;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public final class FrontController extends HttpServlet {
	private final static Logger logger = LoggerFactory.getLogger(FrontController.class);
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
			Command command = (Command) commandMap.get(request.getParameter(COMMAND));
			command.execute(request, response);
		} catch (ControllerException e) {
			request.setAttribute(MESSAGE, "error");
			request.getRequestDispatcher("/WEB-INF/errors/error.jsp").forward(request, response);
		}
	}
}

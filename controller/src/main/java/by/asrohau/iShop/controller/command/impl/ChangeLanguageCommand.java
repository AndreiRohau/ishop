package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ChangeLanguageCommand implements Command {

	private static final Logger logger = Logger.getLogger(ChangeLanguageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info("We got to ChangeLanguageCommand");
		try {
			request.getSession(true).setAttribute("local", request.getParameter("local"));

			String lastCommand = String.valueOf(request.getSession(true).getAttribute(LAST_COMMAND.inString));
			String path = "null".equals(lastCommand) ? INDEX.inString : lastCommand;

			response.sendRedirect(path);
		} catch ( IOException e) { //	ServletException |
			throw new ControllerException(e);
		}
	}
}

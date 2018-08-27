package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.AbstractCommand;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.LAST_COMMAND;

public class ChangeLanguageCommand extends AbstractCommand {

	private static final Logger logger = LoggerFactory.getLogger(ChangeLanguageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info("We got to ChangeLanguageCommand");
		try {
			request.getSession(true).setAttribute("local", request.getParameter("local"));

			String lastCommand = String.valueOf(request.getSession().getAttribute(LAST_COMMAND));
			String path = lastCommand.equals("null") ? "index.jsp" : lastCommand;

			response.sendRedirect(path);
		} catch ( IOException e) {
			throw new ControllerException(e);
		}
	}
}

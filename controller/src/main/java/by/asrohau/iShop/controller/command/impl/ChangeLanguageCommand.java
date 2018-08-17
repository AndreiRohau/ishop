package by.asrohau.iShop.controller.command.impl;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class ChangeLanguageCommand implements Command {

	private static final Logger logger = LoggerFactory.getLogger(ChangeLanguageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(CHANGE_LANGUAGE);
		try {
			request.getSession(true).setAttribute(LOCAL, request.getParameter(LOCAL));

			String lastCommand = String.valueOf(request.getSession().getAttribute(LAST_COMMAND));
			String path = lastCommand.equals(NULL) ? INDEX : lastCommand;

			response.sendRedirect(path);
		} catch ( IOException e) {
			throw new ControllerException(e);
		}
	}
}

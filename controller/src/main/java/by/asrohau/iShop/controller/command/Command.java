package by.asrohau.iShop.controller.command;

import by.asrohau.iShop.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;
	
}

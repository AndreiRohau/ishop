package by.asrohau.iShop.controller.command.parser;

import by.asrohau.iShop.controller.ControllerFinals;
import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class CommandSaxHandler extends DefaultHandler {

    private static final Logger logger = Logger.getLogger(CommandSaxHandler.class);

    private final String PATH = COMMAND_PATH.inString;
    private final String COMMAND_TAG = COMMAND.inString;

    private CommandObj commandObj;
    private StringBuilder text;
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandSaxHandler() {}

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public void startDocument() throws SAXException {
        logger.info("Parsing started.");
    }

    public void endDocument() throws SAXException {
        logger.info("CommandMap includes : " + getCommandMap().size() + " commands");
        logger.info("Parsing ended.");

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (qName.equals(COMMAND_TAG)){
            commandObj = new CommandObj();
        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        ControllerFinals tagName = ControllerFinals.valueOf(qName.toUpperCase().replace("-", "_"));
        switch(tagName){
            case WEBNAME:
                commandObj.setWebName(text.toString());
                break;
            case CLASSNAME:
                commandObj.setClassName(text.toString());
                break;
            case COMMAND:
                try {
                    logger.info(commandObj.getWebName() + " & " + PATH + commandObj.getClassName());

                    commandMap.put(commandObj.getWebName(),
                            (Command) Class.forName(PATH + commandObj.getClassName()).newInstance());
                    commandObj = null;
                    break;
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    new ControllerException(e);
                }

        }
    }

    public void warning(SAXParseException exception) {
        logger.warn("WARNING: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        logger.error("ERROR: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal("FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }
}

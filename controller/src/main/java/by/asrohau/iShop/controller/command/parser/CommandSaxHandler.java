package by.asrohau.iShop.controller.command.parser;

import by.asrohau.iShop.controller.command.Command;
import by.asrohau.iShop.controller.exception.ControllerException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class CommandSaxHandler extends DefaultHandler {

    private final String COMMAND_PATH = "by.asrohau.iShop.controller.command.impl.";
    private final String COMMAND_TAG = "command";

    private CommandObj commandObj;
    private StringBuilder text;
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandSaxHandler() {}

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        System.out.println(getCommandMap().size());
        System.out.println("Parsing ended.");

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

        CommandTagName tagName = CommandTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch(tagName){
            case WEBNAME:
                commandObj.setWebname(text.toString());
                break;
            case CLASSNAME:
                commandObj.setClassname(text.toString());
                break;
            case COMMAND:
                try {
                    System.out.println(commandObj.getWebname() + " & " + COMMAND_PATH + commandObj.getClassname());

                    commandMap.put(commandObj.getWebname(),
                            (Command) Class.forName(COMMAND_PATH + commandObj.getClassname()).newInstance());
                    commandObj = null;
                    break;
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    new ControllerException(e);
                }

        }
    }

    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
        throw (exception);
    }
}

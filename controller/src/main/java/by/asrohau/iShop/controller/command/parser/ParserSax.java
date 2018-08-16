package by.asrohau.iShop.controller.command.parser;

import by.asrohau.iShop.controller.command.Command;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ParserSax {
        private static final String COMMANDS_XML = "Commands.xml";

        public Map<String, Command> getCommandMap() throws SAXException, IOException {

                XMLReader reader = XMLReaderFactory.createXMLReader();

                CommandSaxHandler handler = new CommandSaxHandler();

                reader.setContentHandler(handler);

                InputStream is = getClass().getClassLoader().getResourceAsStream(COMMANDS_XML);

                reader.parse(new InputSource(is));

                return handler.getCommandMap();
        }
}

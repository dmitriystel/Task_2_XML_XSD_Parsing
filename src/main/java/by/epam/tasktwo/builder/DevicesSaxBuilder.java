package by.epam.tasktwo.builder;

import by.epam.tasktwo.handler.DeviceErrorHandler;
import by.epam.tasktwo.handler.DeviceHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DevicesSaxBuilder extends AbstractDeviceBuilder{
    private static final Logger logger = LogManager.getLogger();
    private final SAXParserFactory factory;
    private DeviceHandler handler;

    public DevicesSaxBuilder() {
        super();
        factory = SAXParserFactory.newInstance();
        handler = new DeviceHandler();
    }

    @Override
    public void buildSetDevices(String filename) {
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new DeviceErrorHandler());
            reader.parse(filename);
            deviceSet = handler.getDevices();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(e.getMessage());
        }
    }
}

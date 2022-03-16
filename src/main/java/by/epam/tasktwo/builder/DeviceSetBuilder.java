package by.epam.tasktwo.builder;

import by.epam.tasktwo.entity.Device;
import by.epam.tasktwo.exception.DeviceException;
import by.epam.tasktwo.factory.DeviceBuilderFactory;
import by.epam.tasktwo.validator.DeviceSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class DeviceSetBuilder {
    private static final Logger logger = LogManager.getLogger();

    public Set<Device> createSet(String xmlPath, String xsdPath, String parserType) throws DeviceException {
        Set<Device> devices;
        DeviceSchemaValidator validator = DeviceSchemaValidator.getInstance();
        if(validator.validateXml(xmlPath, xsdPath)){
            AbstractDeviceBuilder builder = DeviceBuilderFactory.createDeviceBuilder(parserType);
            builder.buildSetDevices(xmlPath);
            devices = builder.getDeviceSet();
            return devices;
        } else {
            logger.error("xml not valid (" + xmlPath + ")");
            throw new DeviceException("xml not valid (" + xmlPath + ")");
        }
    }
}

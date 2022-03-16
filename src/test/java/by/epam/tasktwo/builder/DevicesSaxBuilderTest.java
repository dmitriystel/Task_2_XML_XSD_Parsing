package by.epam.tasktwo.builder;

import by.epam.tasktwo.creator.AudioDeviceCreator;
import by.epam.tasktwo.creator.PrintDeviceCreator;
import by.epam.tasktwo.entity.Device;
import by.epam.tasktwo.exception.DeviceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevicesSaxBuilderTest {
    private static final Logger logger = LogManager.getLogger();
    private static final DevicesSaxBuilder saxBuilder = new DevicesSaxBuilder();
    private static final String PATH = "src/test/resources/data/devices_valid.xml";
    private static Set<Device> devices;

    @BeforeAll
    public static void beforeAll() {
        try {
            saxBuilder.buildSetDevices(PATH);
            devices = saxBuilder.getDeviceSet();
        } catch (DeviceException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    void buildSetDevicesAudio() {
        Device expectedDevice = AudioDeviceCreator.createAudioDevice();
        logger.debug(devices);
        assertTrue(devices.contains(expectedDevice));
    }

    @Test
    void buildSetDevicesStorage() {
        Device expectedDevice = PrintDeviceCreator.createStorageDevice();
        assertTrue(devices.contains(expectedDevice));
    }
}

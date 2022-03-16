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

public class DevicesStaxBuilderTest {
    private static final Logger logger = LogManager.getLogger();
    private static final DevicesStaxBuilder staxBuilder = new DevicesStaxBuilder();
    private static final String PATH = "resources/data/devices.xml";
    private static Set<Device> devices;

    @BeforeAll
    public static void beforeAll() {
        try {
            staxBuilder.buildSetDevices(PATH);
            devices = staxBuilder.getDeviceSet();
        } catch (DeviceException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void buildSetDevicesAudio() {
        Device expectedDevice = AudioDeviceCreator.createAudioDevice();
        assertTrue(devices.contains(expectedDevice));
    }

    @Test
    public void buildSetDevicesStorage() {
        Device expectedDevice = PrintDeviceCreator.createStorageDevice();
        assertTrue(devices.contains(expectedDevice));
    }
}

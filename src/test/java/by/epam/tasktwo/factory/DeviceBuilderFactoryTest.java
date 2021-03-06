package by.epam.tasktwo.factory;

import by.epam.tasktwo.builder.AbstractDeviceBuilder;
import by.epam.tasktwo.builder.DevicesDomBuilder;
import by.epam.tasktwo.builder.DevicesSaxBuilder;
import by.epam.tasktwo.builder.DevicesStaxBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeviceBuilderFactoryTest {
    private static final String SAX = "SAX";
    private static final String STAX = "STAX";
    private static final String DOM = "DOM";

    @Test
    void createDeviceStaxBuilder() {
        AbstractDeviceBuilder builder = DeviceBuilderFactory.createDeviceBuilder(STAX);
        assertTrue(builder instanceof DevicesStaxBuilder);
    }

    @Test
    void createDeviceSaxBuilder() {
        AbstractDeviceBuilder builder = DeviceBuilderFactory.createDeviceBuilder(SAX);
        assertTrue(builder instanceof DevicesSaxBuilder);
    }

    @Test
    void createDeviceDomBuilder() {
        AbstractDeviceBuilder builder = DeviceBuilderFactory.createDeviceBuilder(DOM);
        assertTrue(builder instanceof DevicesDomBuilder);
    }

    @Test
    void createInvalidDeviceBuilder() {
        assertThrows(IllegalArgumentException.class, () -> {
            AbstractDeviceBuilder builder = DeviceBuilderFactory.createDeviceBuilder("123");
        });
    }

}

package by.epam.tasktwo.factory;

import by.epam.tasktwo.builder.AbstractDeviceBuilder;
import by.epam.tasktwo.builder.DevicesDomBuilder;
import by.epam.tasktwo.builder.DevicesSaxBuilder;
import by.epam.tasktwo.builder.DevicesStaxBuilder;

public class DeviceBuilderFactory {
    private static DeviceBuilderFactory instance;

    private enum TypeParser{
         DOM, SAX, STAX
    }

    private DeviceBuilderFactory() {
    }

    public static AbstractDeviceBuilder createDeviceBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());

        switch (type){
            case DOM:{
                return new DevicesDomBuilder();
            }
            case SAX:{
                return new DevicesSaxBuilder();
            }
            case STAX:{
                return new DevicesStaxBuilder();
            }
            default:{
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
            }
        }
    }
}

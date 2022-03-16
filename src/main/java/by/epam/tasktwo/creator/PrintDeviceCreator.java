package by.epam.tasktwo.creator;

import by.epam.tasktwo.entity.BrandEnum;
import by.epam.tasktwo.entity.DeviceType;
import by.epam.tasktwo.entity.Ports;
import by.epam.tasktwo.entity.PrintDevice;

import java.time.LocalDate;

public class PrintDeviceCreator {
    public static PrintDevice createStorageDevice(){
        Ports ports = new Ports(false, true, false);
        DeviceType deviceType = new DeviceType(false, 300, false, ports);
        return new PrintDevice.PrintDeviceBuilder()
                .id("d5")
                .title("printer canon")
                .name("Printer")
                .origin("Japan")
                .brand(BrandEnum.CANON.getBrand())
                .price(282.4)
                .type(deviceType)
                .critical(false)
                .release_date(LocalDate.parse("2019-11-01"))
                .cartridgeResource(1600)
                .trayCapacity(150)
                .printSpeed(18)
                .build();
    }
}

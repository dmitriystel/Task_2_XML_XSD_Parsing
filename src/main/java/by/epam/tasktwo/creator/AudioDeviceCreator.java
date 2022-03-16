package by.epam.tasktwo.creator;

import by.epam.tasktwo.entity.AudioDevice;
import by.epam.tasktwo.entity.BrandEnum;
import by.epam.tasktwo.entity.DeviceType;
import by.epam.tasktwo.entity.Ports;

import java.time.LocalDate;

public class AudioDeviceCreator {
    public static AudioDevice createAudioDevice(){
        Ports ports = new Ports(false, false, true);
        DeviceType deviceType = new DeviceType(true, 10, false, ports);
        return new AudioDevice.AudioDeviceBuilder()
                .id("d2")
                .title("speakers bose")
                .name("Speakers")
                .brand(BrandEnum.BOSE.getBrand())
                .origin("USA")
                .price(400.0)
                .type(deviceType)
                .critical(false)
                .release_date(LocalDate.parse("2019-05-01"))
                .wireless(true)
                .surround("5.0")
                .build();
    }
}

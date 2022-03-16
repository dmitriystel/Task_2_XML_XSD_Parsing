package by.epam.tasktwo.handler;

import by.epam.tasktwo.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';
    private Set<Device> devices;
    private Device currentDevice;
    private DeviceXmlTag currentXmlTag;
    private EnumSet<DeviceXmlTag> withText;

    public DeviceHandler() {
        devices = new HashSet<>();
        withText = EnumSet.range(DeviceXmlTag.NAME, DeviceXmlTag.SURROUND);
    }

    public Set<Device> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String audioDeviceTag = DeviceXmlTag.AUDIO_DEVICE.getTagName();
        String storageDeviceTag = DeviceXmlTag.PRINT_DEVICE.getTagName();
        if (audioDeviceTag.equals(qName) || storageDeviceTag.equals(qName)){
            currentDevice = audioDeviceTag.equals(qName) ? new AudioDevice() : new PrintDevice();
            currentDevice.setId(attrs.getValue(DeviceXmlTag.ID.getTagName()));
            String title = attrs.getValue(DeviceXmlTag.TITLE.getTagName());
            if (title == null){
                title = "";
            }
            currentDevice.setTitle(title);
        } else{
            DeviceXmlTag temp = DeviceXmlTag.valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE));
            if(withText.contains(temp)){
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null){
            switch (currentXmlTag){
                case NAME -> currentDevice.setName(data);
                case BRAND -> currentDevice.setBrand(BrandEnum.valueOf(data.toUpperCase().replace(HYPHEN, UNDERSCORE)).getBrand());
                case PRICE -> currentDevice.setPrice(Double.parseDouble(data));
                case CRITICAL -> currentDevice.setCritical(Boolean.parseBoolean(data));
                case RELEASE_DATE -> currentDevice.setRelease_date(LocalDate.parse(data));
                case PERIPHERAL -> currentDevice.getType().setPeripheral(Boolean.parseBoolean(data));
                case POWER_USAGE -> currentDevice.getType().setPowerUsage(Integer.parseInt(data));
                case COOLER -> currentDevice.getType().setCooler(Boolean.parseBoolean(data));
                case COM -> currentDevice.getType().getPorts().setCom(Boolean.parseBoolean(data));
                case USB -> currentDevice.getType().getPorts().setUsb(Boolean.parseBoolean(data));
                case TRS -> currentDevice.getType().getPorts().setTrs(Boolean.parseBoolean(data));

                case CARTRIDGE_RESOURCE -> {
                    PrintDevice printDevice = (PrintDevice) currentDevice;
                    printDevice.setCartridgeResource(Integer.parseInt(data));
                }

                case TRAY_CAPACITY -> {
                    PrintDevice printDevice = (PrintDevice) currentDevice;
                    printDevice.setTrayCapacity(Integer.parseInt(data));
                }

                case PRINT_SPEED -> {
                    PrintDevice printDevice = (PrintDevice) currentDevice;
                    printDevice.setPrintSpeed(Integer.parseInt(data));
                }
                case WIRELESS -> {
                    AudioDevice audioDevice = (AudioDevice) currentDevice;
                    audioDevice.setWireless(Boolean.parseBoolean(data));
                }
                case SURROUND -> {
                    AudioDevice audioDevice = (AudioDevice) currentDevice;
                    audioDevice.setSurround(data);
                }
                default -> logger.error("Unknown tag: " + currentXmlTag);
            }
            currentXmlTag = null;
        }
    }

    @Override

    public void endElement(String uri, String localName, String qName) {
        String audioDeviceTag = DeviceXmlTag.AUDIO_DEVICE.getTagName();
        String storageDeviceTag = DeviceXmlTag.PRINT_DEVICE.getTagName();
        if (audioDeviceTag.equals(qName) || storageDeviceTag.equals(qName)){
            devices.add(currentDevice);
        }
    }
}

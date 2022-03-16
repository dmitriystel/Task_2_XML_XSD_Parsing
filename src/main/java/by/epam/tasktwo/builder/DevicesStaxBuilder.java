package by.epam.tasktwo.builder;

import by.epam.tasktwo.entity.*;
import by.epam.tasktwo.exception.DeviceException;
import by.epam.tasktwo.handler.DeviceXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

import static by.epam.tasktwo.handler.DeviceXmlTag.PRINT_SPEED;
import static by.epam.tasktwo.handler.DeviceXmlTag.TRAY_CAPACITY;

public class DevicesStaxBuilder extends AbstractDeviceBuilder{
    private static final Logger logger = LogManager.getLogger();
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    public DevicesStaxBuilder() {
    }

    @Override
    public void buildSetDevices(String filename) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try(FileInputStream inputStream = new FileInputStream(filename)) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    String name = reader.getLocalName();
                    if (isDeviceTag(name)){
                        Device device = buildDevice(reader);
                        deviceSet.add(device);
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("reading xml error", e);
        } catch (IOException e) {
            logger.error("reading file " + filename + " error");
        } catch (DeviceException e) {
            logger.error("xml file has unknown tag", e);
        }
    }

    private Device buildDevice(XMLStreamReader reader) throws XMLStreamException, DeviceException {
        Device device = reader.getLocalName().equals(DeviceXmlTag.AUDIO_DEVICE.getTagName()) ? new AudioDevice() : new PrintDevice();
        device.setId(reader.getAttributeValue(null, DeviceXmlTag.ID.getTagName()));
        device.setTitle(reader.getAttributeValue(null, DeviceXmlTag.TITLE.getTagName()));
        String name;
        while(reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    DeviceXmlTag currentTag = DeviceXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (currentTag){
                        case NAME -> device.setName(getXMLText(reader));
                        case BRAND -> device.setBrand(getXMLText(reader));
                        case PRICE -> device.setPrice(Double.parseDouble(getXMLText(reader)));
                        case CRITICAL -> device.setCritical(Boolean.parseBoolean(getXMLText(reader)));
                        case RELEASE_DATE -> device.setRelease_date(LocalDate.parse(getXMLText(reader)));
                        case TYPE -> {
                            DeviceType deviceType = buildDeviceType(reader, device);
                            device.setType(deviceType);
                        }
                        case WIRELESS -> {
                            AudioDevice audioDevice = (AudioDevice) device;
                            audioDevice.setWireless(Boolean.parseBoolean(getXMLText(reader)));
                        }
                        case SURROUND -> {
                            AudioDevice audioDevice = (AudioDevice) device;
                            audioDevice.setSurround(getXMLText(reader));
                        }

                        case CARTRIDGE_RESOURCE -> {
                            PrintDevice printDevice = (PrintDevice) device;
                            printDevice.setCartridgeResource(Integer.parseInt(getXMLText(reader)));



                            case TRAY_CAPACITY -> {
                                PrintDevice printDevice = (PrintDevice) device;
                                printDevice.setTrayCapacity(Integer.parseInt(getXMLText(reader)));
                            }

                            case PRINT_SPEED -> {
                                PrintDevice printDevice = (PrintDevice) device;
                                printDevice.setPrintSpeed(Integer.parseInt(getXMLText(reader)));
                            }


                        default -> {
                            logger.error("Unknown tag: " + currentTag);
                            throw new DeviceException("Unknown tag: " + currentTag);
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (isDeviceTag(name)){
                        return device;
                    }
                }
            }
        }
        return device;
    }


    private DeviceType buildDeviceType(XMLStreamReader reader, Device device) throws XMLStreamException, DeviceException {
        DeviceType deviceType = device.getType();
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    String data = getXMLText(reader);
                    DeviceXmlTag currentTag = DeviceXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (currentTag){
                        case PERIPHERAL -> deviceType.setPeripheral(Boolean.parseBoolean(data));
                        case POWER_USAGE -> deviceType.setPowerUsage(Integer.parseInt(data));
                        case COOLER -> deviceType.setCooler(Boolean.parseBoolean(data));
                        case PORTS -> {
                            Ports ports = buildDevicePorts(reader, device);
                            deviceType.setPorts(ports);
                        }
                        default -> logger.error("tag not found");
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(DeviceXmlTag.TYPE.getTagName())){
                        return deviceType;
                    }
                }
            }
        }
        throw new DeviceException("unknown element in tag <type>");
    }


    private Ports buildDevicePorts(XMLStreamReader reader, Device device) throws XMLStreamException, DeviceException {
        Ports ports = device.getType().getPorts();
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    String data = getXMLText(reader);
                    DeviceXmlTag currentTag = DeviceXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (currentTag){
                        case COM -> ports.setCom(Boolean.parseBoolean(data));
                        case USB -> ports.setUsb(Boolean.parseBoolean(data));
                        case TRS -> ports.setTrs(Boolean.parseBoolean(data));
                        default -> logger.error("tag not found");
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(DeviceXmlTag.PORTS.getTagName())){
                        return ports;
                    }
                }
            }
        }
        throw new DeviceException("unknown element int tag <ports>");
    }

    private String getXMLContent(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        return getXMLContent(reader).strip();
    }

    private boolean isDeviceTag(String tag){
        return DeviceXmlTag.AUDIO_DEVICE.getTagName().equals(tag) || DeviceXmlTag.PRINT_DEVICE.getTagName().equals(tag);
    }
}

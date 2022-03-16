package by.epam.tasktwo.handler;

public enum DeviceXmlTag {
    ID("id"),
    TITLE("title"),
    DEVICES("devices"),
    AUDIO_DEVICE("audio-device"),
    PRINT_DEVICE("print-device"),

    NAME("name"),
    BRAND("brand"),
    ORIGIN("origin"),
    PRICE("price"),

    PERIPHERAL("peripheral"),
    POWER_USAGE("power-usage"),
    COOLER("cooler"),

    PORTS("ports"),
    COM("com"),
    USB("usb"),
    TRS("trs"),


    CRITICAL("critical"),
    WIRELESS("wireless"),
    SURROUND("surround"),
    RELEASE_DATE("release-date"),
    TYPE("type"),

    CARTRIDGE_RESOURCE("cartridge-resource"),
    TRAY_CAPACITY("tray-capacity"),
    PRINT_SPEED("print-speed");

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    private String name;
    DeviceXmlTag(String name){
        this.name = name;
    }

    public String getTagName(){
        String tagName = this.name();
        tagName = tagName.toLowerCase();
        tagName = tagName.replace(UNDERSCORE, HYPHEN);
        return tagName;
    }
}

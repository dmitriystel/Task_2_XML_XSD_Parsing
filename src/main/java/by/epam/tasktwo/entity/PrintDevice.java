package by.epam.tasktwo.entity;

import java.time.LocalDate;
import java.util.Objects;

public class PrintDevice extends Device {
    private boolean wireless;
    private int cartridgeResource;
    private int trayCapacity;
    private int printSpeed;

    public PrintDevice() {
        super();
    }

    public PrintDevice(PrintDeviceBuilder builder) {
        super(builder.id, builder.title, builder.name, builder.brand, builder.origin, builder.price, builder.type,
                builder.critical, builder.release_date);
        this.wireless = wireless;
        this.cartridgeResource = cartridgeResource;
        this.trayCapacity = trayCapacity;
        this.printSpeed = printSpeed;
    }

    public boolean isWireless() {
        return wireless;
    }

    public int getCartridgeResource() {
        return cartridgeResource;
    }

    public int getTrayCapacity() {
        return trayCapacity;
    }

    public int getPrintSpeed() {
        return printSpeed;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    public void setCartridgeResource(int cartridgeResource) {
        this.cartridgeResource = cartridgeResource;
    }

    public void setTrayCapacity(int trayCapacity) {
        this.trayCapacity = trayCapacity;
    }

    public void setPrintSpeed(int printSpeed) {
        this.printSpeed = printSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrintDevice)) return false;
        if (!super.equals(o)) return false;
        PrintDevice that = (PrintDevice) o;
        return isWireless() == that.isWireless() && getCartridgeResource() == that.getCartridgeResource() && getTrayCapacity() == that.getTrayCapacity() && getPrintSpeed() == that.getPrintSpeed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isWireless(), getCartridgeResource(), getTrayCapacity(), getPrintSpeed());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrintDevice{");
        sb.append(super.toString());
        sb.append(", wireless=").append(wireless);
        sb.append(", cartridgeResource=").append(cartridgeResource);
        sb.append(", trayCapacity=").append(trayCapacity);
        sb.append(", printSpeed=").append(printSpeed);
        sb.append('}');
        return sb.toString();
    }

    public static class PrintDeviceBuilder extends Device.DeviceBuilder<PrintDeviceBuilder>{

        private boolean wireless;
        private int cartridgeResource;
        private int trayCapacity;
        private int printSpeed;

        public PrintDeviceBuilder(){
        }
        public PrintDeviceBuilder wireless(Boolean wireless) {
            this.wireless = wireless;
            return this;
        }

        public PrintDeviceBuilder cartridgeResource(int cartridgeResource){
            this.cartridgeResource = cartridgeResource;
            return this;
        }

        public PrintDeviceBuilder trayCapacity(int trayCapacity){
            this.trayCapacity = trayCapacity;
            return this;
        }

        public PrintDeviceBuilder printSpeed(int printSpeed){
            this.printSpeed = printSpeed;
            return this;
        }

        @Override
        public PrintDevice build(){
            return new PrintDevice(this);
        }
    }
}

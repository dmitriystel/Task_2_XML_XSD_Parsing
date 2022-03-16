package by.epam.tasktwo.entity;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Device {
    private String id;
    private String title;
    private String name;
    private String brand;
    private String origin;
    private double price;
    private DeviceType type;
    private boolean critical;
    private LocalDate release_date;

    public Device() {
        type = new DeviceType();
    }

    public Device(String id, String title, String name, String brand, String origin, double price, DeviceType type,
                  boolean critical, LocalDate release_date) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.brand = brand;
        this.origin = origin;
        this.price = price;
        this.type = type;
        this.critical = critical;
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getOrigin() {
        return origin;
    }

    public double getPrice() {
        return price;
    }

    public DeviceType getType() {
        return type;
    }

    public boolean isCritical() {
        return critical;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Double.compare(device.price, price) == 0 && critical == device.critical && Objects.equals(id, device.id) && Objects.equals(name, device.name) && Objects.equals(brand, device.brand) && Objects.equals(origin, device.origin) && Objects.equals(type, device.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, origin, price, type, critical);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Device{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", origin='").append(origin).append('\'');
        sb.append(", price=").append(price);
        sb.append(", type=").append(type);
        sb.append(", critical=").append(critical);
        sb.append('}');
        return sb.toString();
    }

    protected static abstract class DeviceBuilder<B extends DeviceBuilder<?>>{
        String id;
        String title;
        String name;
        String brand;
        String origin;
        double price;
        DeviceType type;
        boolean critical;
        LocalDate release_date;

        public B id(String deviceId){
            this.id = deviceId;
            return (B) this;
        }

        public B title(String title){
            this.title = title;
            return (B) this;
        }

        public B name(String name){
            this.name = name;
            return (B) this;
        }

        public B brand(String brand){
            this.brand = brand;
            return (B) this;
        }

        public B origin(String brand){
            this.origin = origin;
            return (B) this;
        }

        public B price(Double price){
            this.price = price;
            return (B) this;
        }

        public B type(DeviceType type){
            this.type = type;
            return (B) this;
        }

        public B critical(Boolean critical){
            this.critical = critical;
            return (B) this;
        }

        public B release_date(LocalDate warranty){
            this.release_date = release_date;
            return (B) this;
        }

        public abstract Device build();
    }
}

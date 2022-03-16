package by.epam.tasktwo.entity;

import java.util.Objects;

public class DeviceType {

    private boolean peripheral;
    private int powerUsage;
    private boolean cooler;
    private Ports ports;

    public DeviceType() {
        ports = new Ports();
    }

    public DeviceType(boolean peripheral, int powerUsage, boolean cooler, Ports ports) {
        this.peripheral = peripheral;
        this.powerUsage = powerUsage;
        this.cooler = cooler;
        this.ports = ports;
    }

    public boolean isPeripheral() {
        return peripheral;
    }

    public int getPowerUsage() {
        return powerUsage;
    }

    public boolean isCooler() {
        return cooler;
    }

    public Ports getPorts() {
        return ports;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }

    public void setPowerUsage(int powerUsage) {
        this.powerUsage = powerUsage;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    public void setPorts(Ports ports) {
        this.ports = ports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceType that = (DeviceType) o;
        return peripheral == that.peripheral && powerUsage == that.powerUsage && cooler == that.cooler && Objects.equals(ports, that.ports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peripheral, powerUsage, cooler, ports);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceType{");
        sb.append("peripheral=").append(peripheral);
        sb.append(", powerUsage=").append(powerUsage);
        sb.append(", cooler=").append(cooler);
        sb.append(", ports=").append(ports);
        sb.append('}');
        return sb.toString();
    }
}


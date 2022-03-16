package by.epam.tasktwo.entity;

import java.util.Objects;

public class Ports {
    boolean com;
    boolean usb;
    boolean trs;

    public Ports() {
    }

    public Ports(boolean com, boolean usb, boolean trs) {
        this.com = com;
        this.usb = usb;
        this.trs = trs;
    }

    public boolean isCom() {
        return com;
    }

    public boolean isUsb() {
        return usb;
    }

    public boolean isTrs() {
        return trs;
    }

    public void setCom(boolean com) {
        this.com = com;
    }

    public void setUsb(boolean usb) {
        this.usb = usb;
    }

    public void setTrs(boolean trs) {
        this.trs = trs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ports ports = (Ports) o;
        return com == ports.com && usb == ports.usb && trs == ports.trs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(com, usb, trs);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ports{");
        sb.append("com=").append(com);
        sb.append(", usb=").append(usb);
        sb.append(", trs=").append(trs);
        sb.append('}');
        return sb.toString();
    }
}

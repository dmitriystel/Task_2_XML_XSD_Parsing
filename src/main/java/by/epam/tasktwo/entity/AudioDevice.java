package by.epam.tasktwo.entity;

import java.util.Objects;

public class AudioDevice extends Device{
    private String surround;
    private boolean wireless;

    public AudioDevice() {
        super();
    }

    public AudioDevice(AudioDevice.AudioDeviceBuilder builder) {
        super(builder.id, builder.title, builder.name, builder.brand, builder.origin, builder.price, builder.type,
                builder.critical, builder.release_date);
        this.surround = builder.surround;
        this.wireless = builder.wireless;
    }

    public String getSurround() {
        return surround;
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setSurround(String surround) {
        this.surround = surround;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AudioDevice that = (AudioDevice) o;
        return wireless == that.wireless && Objects.equals(surround, that.surround);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), surround, wireless);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AudioDevice{");
        sb.append(super.toString());
        sb.append(", surround=").append(surround);
        sb.append(", wireless=").append(wireless);
        sb.append('}');
        return sb.toString();
    }

    public static class AudioDeviceBuilder extends Device.DeviceBuilder<AudioDevice.AudioDeviceBuilder> {
        private String surround;
        private boolean wireless;

        public AudioDeviceBuilder() {
        }

        public AudioDeviceBuilder surround(String surround) {
            this.surround = surround;
            return this;
        }

        public AudioDeviceBuilder wireless(Boolean wireless) {
            this.wireless = wireless;
            return this;
        }

        @Override
        public AudioDevice build() {
            return new AudioDevice(this);
        }
    }
}


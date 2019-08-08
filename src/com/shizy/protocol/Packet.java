package com.shizy.protocol;

public abstract class Packet {

    /**
     * 协议版本
     */
    private transient byte version = 1;

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public abstract byte getCommand();

}

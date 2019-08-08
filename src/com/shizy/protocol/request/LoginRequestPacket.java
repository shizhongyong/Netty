package com.shizy.protocol.request;

import com.shizy.protocol.command.Command;
import com.shizy.protocol.Packet;

public class LoginRequestPacket extends Packet {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

}

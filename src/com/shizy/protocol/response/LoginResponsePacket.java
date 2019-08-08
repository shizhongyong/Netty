package com.shizy.protocol.response;

import com.shizy.protocol.command.Command;
import com.shizy.protocol.Packet;

public class LoginResponsePacket extends Packet {

    private String userId;
    private String username;
    private boolean success;
    private String reason;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}

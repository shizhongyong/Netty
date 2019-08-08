package com.shizy.protocol.response;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;

public class LogoutResponsePacket extends Packet {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}

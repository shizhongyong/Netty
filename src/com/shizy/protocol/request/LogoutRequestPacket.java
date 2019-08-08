package com.shizy.protocol.request;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;

public class LogoutRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }

}

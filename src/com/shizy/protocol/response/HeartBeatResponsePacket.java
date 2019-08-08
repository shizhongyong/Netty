package com.shizy.protocol.response;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}

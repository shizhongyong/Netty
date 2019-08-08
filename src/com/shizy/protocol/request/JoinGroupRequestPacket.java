package com.shizy.protocol.request;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;

public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}

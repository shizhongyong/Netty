package com.shizy.protocol.request;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;

public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}

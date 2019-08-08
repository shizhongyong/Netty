package com.shizy.protocol.response;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;
import com.shizy.session.Session;

import java.util.List;

public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;
    private List<Session> members;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Session> getMembers() {
        return members;
    }

    public void setMembers(List<Session> members) {
        this.members = members;
    }

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}

package com.shizy.protocol.request;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;

import java.util.List;

public class CreateGroupRequestPacket extends Packet {

    private String creatorId;

    private List<String> memberIds;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}

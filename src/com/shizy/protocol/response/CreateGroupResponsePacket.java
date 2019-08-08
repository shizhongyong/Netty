package com.shizy.protocol.response;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;
import com.shizy.session.Session;

import java.util.List;

public class CreateGroupResponsePacket extends Packet {

    private String groupId;
    private String creatorId;
    private List<Session> members;
    private boolean success;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<Session> getMembers() {
        return members;
    }

    public void setMembers(List<Session> members) {
        this.members = members;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}

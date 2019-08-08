package com.shizy.protocol.response;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;
import com.shizy.session.Session;

public class JoinGroupResponsePacket extends Packet {

    private String groupId;
    private Session member;
    private boolean success;
    private String reason;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Session getMember() {
        return member;
    }

    public void setMember(Session member) {
        this.member = member;
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
        return Command.JOIN_GROUP_RESPONSE;
    }
}

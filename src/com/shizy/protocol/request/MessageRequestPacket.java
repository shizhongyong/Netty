package com.shizy.protocol.request;

import com.shizy.protocol.Packet;
import com.shizy.protocol.command.Command;
import com.shizy.session.Session;

public class MessageRequestPacket extends Packet {

    private Session from;
    private String toId;
    private String message;
    private boolean isGroup;

    public Session getFrom() {
        return from;
    }

    public void setFrom(Session from) {
        this.from = from;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    @Override
    public byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}

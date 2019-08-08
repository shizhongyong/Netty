package com.shizy.client.handler;

import com.shizy.protocol.response.ListGroupMembersResponsePacket;
import com.shizy.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) throws Exception {
        System.out.println("群[" + responsePacket.getGroupId() + "]中的人包括：");
        final List<Session> sessions = responsePacket.getMembers();
        for (Session session : sessions) {
            System.out.print(session.getUserName() + " ");
        }
        System.out.println();
    }
}

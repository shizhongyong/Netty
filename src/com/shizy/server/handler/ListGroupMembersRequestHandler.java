package com.shizy.server.handler;

import com.shizy.protocol.request.ListGroupMembersRequestPacket;
import com.shizy.protocol.response.ListGroupMembersResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
        final Session session = SessionUtil.getSession(ctx.channel());
        System.out.println(session.getUserName() + "正在获取群[" + requestPacket.getGroupId() + "]的成员列表");

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(requestPacket.getGroupId());
        final List<Session> members = new ArrayList<>();
        for (Channel channel : channelGroup) {
            members.add(SessionUtil.getSession(channel));
        }

        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setMembers(members);

        ctx.channel().writeAndFlush(responsePacket);
    }
}

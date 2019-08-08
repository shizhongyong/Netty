package com.shizy.server.handler;

import com.shizy.protocol.request.CreateGroupRequestPacket;
import com.shizy.protocol.response.CreateGroupResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    private static int sGroupIdIndex = 1;

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket requestPacket) throws Exception {
        System.out.println("服务端创建群！");

        final Session session = SessionUtil.getSession(ctx.channel());
        final List<String> ids = new ArrayList<>();
        ids.add(session.getUserId());
        ids.addAll(requestPacket.getMemberIds());

        final List<Session> members = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String id : ids) {
            Channel channel = SessionUtil.getChannel(id);
            if (channel != null && SessionUtil.hasLogin(channel)) {
                channelGroup.add(channel);
                members.add(SessionUtil.getSession(channel));
            }
        }

        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();

        final String groupId = String.valueOf(sGroupIdIndex++);
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        responsePacket.setCreatorId(requestPacket.getCreatorId());
        responsePacket.setMembers(members);

        channelGroup.writeAndFlush(responsePacket);

        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }

}

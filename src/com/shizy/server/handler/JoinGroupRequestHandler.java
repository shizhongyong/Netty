package com.shizy.server.handler;

import com.shizy.protocol.request.JoinGroupRequestPacket;
import com.shizy.protocol.response.JoinGroupResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) throws Exception {
        final Session session = SessionUtil.getSession(ctx.channel());
        System.out.println(session.getUserName() + "加入群聊！");

        final String groupId = requestPacket.getGroupId();
        ChannelGroup group = SessionUtil.getChannelGroup(groupId);
        group.add(ctx.channel());

        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}

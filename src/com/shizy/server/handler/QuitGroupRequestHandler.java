package com.shizy.server.handler;

import com.shizy.protocol.request.QuitGroupRequestPacket;
import com.shizy.protocol.response.QuitGroupResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {
        
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) throws Exception {
        final Session session = SessionUtil.getSession(ctx.channel());
        System.out.println(session.getUserName() + "退出群聊！");

        final String groupId = requestPacket.getGroupId();
        ChannelGroup group = SessionUtil.getChannelGroup(groupId);
        group.remove(ctx.channel());

        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}

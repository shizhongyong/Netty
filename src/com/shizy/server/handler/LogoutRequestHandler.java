package com.shizy.server.handler;

import com.shizy.protocol.request.LogoutRequestPacket;
import com.shizy.protocol.response.LogoutResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        final Session session = SessionUtil.getSession(ctx.channel());
        System.out.println(session.getUserName() + "退出登录！");

        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket responsePacket = new LogoutResponsePacket();
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}

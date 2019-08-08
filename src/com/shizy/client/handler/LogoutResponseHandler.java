package com.shizy.client.handler;

import com.shizy.protocol.response.LogoutResponsePacket;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket responsePacket) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("已退出登录!");
    }

}

package com.shizy.client.handler;

import com.shizy.protocol.request.LoginRequestPacket;
import com.shizy.protocol.response.LoginResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.LoginUtil;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            final String userId = responsePacket.getUserId();
            final String username = responsePacket.getUsername();
            System.out.println(new Date() + ": 客户端登录成功！userId = " + userId);
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败！原因：" + responsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
        super.channelInactive(ctx);
    }
}

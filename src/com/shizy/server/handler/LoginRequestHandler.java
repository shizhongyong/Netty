package com.shizy.server.handler;

import com.shizy.protocol.request.LoginRequestPacket;
import com.shizy.protocol.response.LoginResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static int sUserIdIndex = 1;

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
        
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket requestPacket) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录...");

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(requestPacket.getVersion());
        responsePacket.setUsername(requestPacket.getUsername());
        if (isValid(requestPacket)) {
            System.out.println(requestPacket.getUsername() + "登录成功！");
            responsePacket.setSuccess(true);
            responsePacket.setUserId(String.valueOf(sUserIdIndex++));
//            responsePacket.setUserId(UUID.randomUUID().toString());

            final Session session = new Session(responsePacket.getUserId(), responsePacket.getUsername());
            SessionUtil.bindSession(session, ctx.channel());
        } else {
            System.out.println(requestPacket.getUsername() + "登录失败！");
            responsePacket.setSuccess(false);
            responsePacket.setReason("账号或密码错误！");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        super.channelInactive(ctx);
    }

    private boolean isValid(LoginRequestPacket requestPacket) {
        return requestPacket.getUsername().equals(requestPacket.getPassword());
    }
}

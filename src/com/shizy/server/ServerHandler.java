package com.shizy.server;

import com.shizy.protocol.request.LoginRequestPacket;
import com.shizy.protocol.request.MessageRequestPacket;
import com.shizy.protocol.response.LoginResponsePacket;
import com.shizy.protocol.Packet;
import com.shizy.protocol.PacketCodec;
import com.shizy.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + ": 客户端开始登录...");

            LoginRequestPacket requestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setVersion(requestPacket.getVersion());
            if (isValid(requestPacket)) {
                System.out.println("登录成功！");
                responsePacket.setSuccess(true);
                responsePacket.setUserId(UUID.randomUUID().toString());
            } else {
                System.out.println("登录失败！");
                responsePacket.setSuccess(false);
                responsePacket.setReason("账号或密码错误！");
            }
            ByteBuf response = PacketCodec.INSTANCE.encode(responsePacket);
            ctx.channel().writeAndFlush(response);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket requestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到客户端的消息: " + requestPacket.getMessage());

            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setVersion(requestPacket.getVersion());
            responsePacket.setMessage("服务端回复[" + requestPacket.getMessage() + "]");

            ByteBuf response = PacketCodec.INSTANCE.encode(responsePacket);
            ctx.channel().writeAndFlush(response);
        }

        byteBuf.release();
    }

    private boolean isValid(LoginRequestPacket requestPacket) {
        return requestPacket.getUsername().equals(requestPacket.getPassword());
    }

}

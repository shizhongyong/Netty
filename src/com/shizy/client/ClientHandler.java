package com.shizy.client;

import com.shizy.protocol.Packet;
import com.shizy.protocol.PacketCodec;
import com.shizy.protocol.request.LoginRequestPacket;
import com.shizy.protocol.response.LoginResponsePacket;
import com.shizy.protocol.response.MessageResponsePacket;
import com.shizy.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录！");

        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUsername("shizy");
        packet.setPassword("shizy");

        ByteBuf byteBuf = PacketCodec.INSTANCE.encode(packet);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket responsePacket = (LoginResponsePacket) packet;
            if (responsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功！");
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                System.out.println(new Date() + ": 客户端登录失败！原因：" + responsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket responsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端消息：" + responsePacket.getMessage());
        }

        byteBuf.release();
    }
}

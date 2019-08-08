package com.shizy.client.handler;

import com.shizy.protocol.response.MessageResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket responsePacket) throws Exception {
        if (responsePacket.isGroup()) {
            final Session session = SessionUtil.getSession(ctx.channel());
            if (session.getUserId().equals(responsePacket.getFrom().getUserId())) {// 如果是自己发的
                // System.out.println("群消息发送成功！");
            } else {
                System.out.println("收到群[" + responsePacket.getToId() + "]中[" + responsePacket.getFrom().getUserName() + "]发的消息：" + responsePacket.getMessage());
            }
        } else {
            System.out.println(new Date() + ": 收到[" + responsePacket.getFrom().getUserName() + "]的消息：" + responsePacket.getMessage());
        }
    }
}

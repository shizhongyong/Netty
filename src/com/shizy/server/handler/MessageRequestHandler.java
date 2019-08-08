package com.shizy.server.handler;

import com.shizy.protocol.request.MessageRequestPacket;
import com.shizy.protocol.response.MessageResponsePacket;
import com.shizy.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.Date;

@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket requestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端的消息: " + requestPacket.getMessage());

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setVersion(requestPacket.getVersion());
        responsePacket.setFrom(requestPacket.getFrom());
        responsePacket.setToId(requestPacket.getToId());
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setGroup(requestPacket.isGroup());

        if (responsePacket.isGroup()) {
            ChannelGroup channelGroup = SessionUtil.getChannelGroup(requestPacket.getToId());
            channelGroup.writeAndFlush(responsePacket);
        } else {
            Channel toUserChannel = SessionUtil.getChannel(requestPacket.getToId());

            // 4.将消息发送给消息接收方
            if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
                toUserChannel.writeAndFlush(responsePacket);
            } else {
                System.err.println("[" + requestPacket.getToId() + "] 不在线，发送失败!");
            }
        }
    }
}

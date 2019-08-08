package com.shizy.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "|||客户端发送数据");

        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("Hi, This is me!".getBytes(CharsetUtil.UTF_8));
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.print(new Date() + "|||客户端收到数据：");
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }
}

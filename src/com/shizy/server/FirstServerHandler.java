package com.shizy.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        if (byteBuf.readableBytes() > 0) {
            System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(CharsetUtil.UTF_8));
        }
        byteBuf.release();

        byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("Server Response".getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }

}


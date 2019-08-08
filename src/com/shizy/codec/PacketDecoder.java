package com.shizy.codec;

import com.shizy.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
@ChannelHandler.Sharable
public class PacketDecoder extends ByteToMessageDecoder {

    public static final PacketDecoder INSTANCE = new PacketDecoder();

    private PacketDecoder() {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodec.INSTANCE.decode(in));
    }
}

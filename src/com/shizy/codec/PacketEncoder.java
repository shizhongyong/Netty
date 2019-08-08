package com.shizy.codec;

import com.shizy.protocol.Packet;
import com.shizy.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    public static final PacketEncoder INSTANCE = new PacketEncoder();

    private PacketEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, msg);
    }
}

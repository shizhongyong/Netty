package com.shizy.server;

import com.shizy.codec.PacketCodecHandler;
import com.shizy.codec.PacketDecoder;
import com.shizy.codec.PacketEncoder;
import com.shizy.codec.Spliter;
import com.shizy.handler.IMIdleStateHandler;
import com.shizy.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.AttributeKey;

public class NettyServer {

    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            final ServerBootstrap bootstrap = new ServerBootstrap();
            final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .attr(AttributeKey.newInstance("serverName"), "Netty")
                    .childAttr(clientKey, "clientValue")
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IMIdleStateHandler());
                            ch.pipeline().addLast(new Spliter());
                            ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
//                            ch.pipeline().addLast(new LifecycleHandler());
                            ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                            ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                            ch.pipeline().addLast(MessageRequestHandler.INSTANCE);
                            ch.pipeline().addLast(CreateGroupRequestHandler.INSTANCE);
                            ch.pipeline().addLast(JoinGroupRequestHandler.INSTANCE);
                            ch.pipeline().addLast(QuitGroupRequestHandler.INSTANCE);
                            ch.pipeline().addLast(ListGroupMembersRequestHandler.INSTANCE);
                            ch.pipeline().addLast(LogoutRequestHandler.INSTANCE);
                        }
                    });

            ChannelFuture cf = bootstrap.bind(SERVER_PORT).sync();

            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}

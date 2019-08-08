package com.shizy.client;

import com.shizy.client.console.ConsoleCommandManager;
import com.shizy.client.handler.*;
import com.shizy.codec.PacketCodecHandler;
import com.shizy.codec.PacketDecoder;
import com.shizy.codec.PacketEncoder;
import com.shizy.codec.Spliter;
import com.shizy.handler.IMIdleStateHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Scanner;

public class NettyClient {

    public static final int MAX_RETRY = 5;

    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("clientName"), "NettyClient")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(new LoginResponseHandler());
                        // 心跳定时器
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                    }
                });

        bootstrap.connect("127.0.0.1", 9999).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Connect Success!");
                    startMessageThread(future.channel());
                } else {
                    System.out.println("Connect failed!");
                }
            }
        });
    }

    private static void startMessageThread(Channel channel) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            ConsoleCommandManager manager = new ConsoleCommandManager();

            while (!Thread.interrupted()) {
                manager.exec(channel, scanner);
            }
        }).start();
    }

}

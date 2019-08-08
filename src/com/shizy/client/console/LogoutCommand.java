package com.shizy.client.console;

import com.shizy.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutCommand implements ConsoleCommand {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        channel.writeAndFlush(new LogoutRequestPacket());
    }
}

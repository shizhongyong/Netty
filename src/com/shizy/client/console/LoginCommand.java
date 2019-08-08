package com.shizy.client.console;

import com.shizy.protocol.request.LoginRequestPacket;
import com.shizy.util.LoginUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginCommand implements ConsoleCommand {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        if (LoginUtil.hasLogin(channel)) {
            System.out.println("您已登录！");
        } else {
            System.out.println("输入用户名登录: ");

            final String username = scanner.nextLine();

            LoginRequestPacket packet = new LoginRequestPacket();
            packet.setUsername(username);
            packet.setPassword(username);

            channel.writeAndFlush(packet);
        }
    }
}

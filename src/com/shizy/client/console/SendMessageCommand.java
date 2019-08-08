package com.shizy.client.console;

import com.shizy.protocol.request.MessageRequestPacket;
import com.shizy.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendMessageCommand implements ConsoleCommand {

    @Override
    public void exec(Channel channel, Scanner scanner) {
        if (SessionUtil.hasLogin(channel)) {
            MessageRequestPacket packet = new MessageRequestPacket();
            packet.setFrom(SessionUtil.getSession(channel));
            packet.setGroup(false);

            System.out.println("输入收信人Id: ");
            String toId = scanner.nextLine();
            packet.setToId(toId);

            System.out.println("输入信息内容: ");
            String message = scanner.nextLine();
            packet.setMessage(message);

            channel.writeAndFlush(packet);
        } else {
            System.out.println("请先登录！！！");
        }
    }
}

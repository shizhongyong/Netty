package com.shizy.client.console;

import com.shizy.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupCommand implements ConsoleCommand {

    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.println("输入 groupId，加入群聊：");
        String groupId = scanner.nextLine();

        JoinGroupRequestPacket packet = new JoinGroupRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }

}

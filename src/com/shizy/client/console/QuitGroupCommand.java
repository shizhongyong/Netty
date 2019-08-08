package com.shizy.client.console;

import com.shizy.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class QuitGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.println("输入 groupId，退出群聊：");
        String groupId = scanner.nextLine();

        QuitGroupRequestPacket packet = new QuitGroupRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}

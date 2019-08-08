package com.shizy.client.console;

import com.shizy.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ListGroupMembersCommand implements ConsoleCommand {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.println("输入 groupId，获取群成员列表：");
        String groupId = scanner.nextLine();

        ListGroupMembersRequestPacket packet = new ListGroupMembersRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}

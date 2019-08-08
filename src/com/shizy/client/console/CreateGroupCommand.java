package com.shizy.client.console;

import com.shizy.protocol.request.CreateGroupRequestPacket;
import com.shizy.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupCommand implements ConsoleCommand {

    @Override
    public void exec(Channel channel, Scanner scanner) {
        if (SessionUtil.hasLogin(channel)) {
            CreateGroupRequestPacket packet = new CreateGroupRequestPacket();

            System.out.println("请输入成员Id，使用(,)分隔: ");
            String[] ids = scanner.nextLine().split(",");

            packet.setCreatorId(SessionUtil.getSession(channel).getUserId());
            packet.setMemberIds(Arrays.asList(ids));

            channel.writeAndFlush(packet);
        } else {
            System.out.println("请先登录！！！");
        }
    }

}

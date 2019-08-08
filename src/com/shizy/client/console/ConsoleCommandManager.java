package com.shizy.client.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    private static final Map<String, ConsoleCommand> commandMap = new HashMap<>();

    static {
        commandMap.put("login", new LoginCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("message", new SendMessageCommand());
        commandMap.put("groupMessage", new SendGroupMessageCommand());
        commandMap.put("createGroup", new CreateGroupCommand());
        commandMap.put("joinGroup", new JoinGroupCommand());
        commandMap.put("quitGroup", new QuitGroupCommand());
        commandMap.put("listGroupMembers", new ListGroupMembersCommand());
    }

    @Override
    public void exec(Channel channel, Scanner scanner) {
        final String input = scanner.nextLine();
        ConsoleCommand command = commandMap.get(input);
        if (command == null) {
            help();
        } else {
            command.exec(channel, scanner);
        }
    }

    private void help() {
        System.out.println("仅支持以下命令：");
        for (String command : commandMap.keySet()) {
            System.out.println("\t" + command);
        }
    }

}

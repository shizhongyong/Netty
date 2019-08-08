package com.shizy.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {

    void exec(Channel channel, Scanner scanner);

}

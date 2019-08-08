package com.shizy.client.handler;

import com.shizy.protocol.response.CreateGroupResponsePacket;
import com.shizy.session.Session;
import com.shizy.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            final Session session = SessionUtil.getSession(ctx.channel());
            if (session.getUserId().equals(responsePacket.getCreatorId())) {
                System.out.println("创建群成功：groupId = " + responsePacket.getGroupId());
            } else {
                System.out.println("您已被拉入群：" + responsePacket.getGroupId());
            }
        } else {
            System.out.println("创建群失败！");
        }
    }

}

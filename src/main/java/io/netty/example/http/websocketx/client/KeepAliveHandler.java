package io.netty.example.http.websocketx.client;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by Димон on 25.05.2018.
 */
public class KeepAliveHandler extends ChannelDuplexHandler {
    @Override
    public void userEventTriggered ( ChannelHandlerContext ctx, Object evt ) throws Exception {
        if (evt instanceof IdleStateEvent ) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                System.out.println ("Reading...");
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                System.out.println ("Pinging...");
                ctx.writeAndFlush(new PingWebSocketFrame ());
            }
        }
    }
}

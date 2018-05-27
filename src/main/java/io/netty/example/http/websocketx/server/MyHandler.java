package io.netty.example.http.websocketx.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by Димон on 25.05.2018.
 */
public class MyHandler extends WebSocketServerProtocolHandler {


    public MyHandler ( String websocketPath, String subprotocols, boolean allowExtensions ) {
        super ( websocketPath, subprotocols, allowExtensions );
    }

    @Override
    public void channelRead ( ChannelHandlerContext ctx, Object msg ) throws Exception {
        ctx.channel ().writeAndFlush ( msg );
    }


}

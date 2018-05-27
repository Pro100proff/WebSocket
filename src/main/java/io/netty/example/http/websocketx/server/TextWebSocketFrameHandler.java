package io.netty.example.http.websocketx.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * Created by Димон on 26.05.2018.
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler <TextWebSocketFrame> {
    private ChannelGroup channels;

    public TextWebSocketFrameHandler ( ChannelGroup channels ) {
        this.channels = channels;
    }

    @Override
    public void userEventTriggered ( ChannelHandlerContext ctx, Object evt ) throws Exception {
        if ( evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE ) {
            channels.add ( ctx.channel () );
            channels.writeAndFlush ( new TextWebSocketFrame ( "Connected new client " + ctx.channel () ) );
        }
    }

    @Override
    protected void channelRead0 ( ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame ) throws Exception {
        channels.writeAndFlush ( textWebSocketFrame );
    }
}

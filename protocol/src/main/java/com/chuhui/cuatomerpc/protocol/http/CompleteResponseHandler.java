package com.chuhui.cuatomerpc.protocol.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * CompleteResponseHandler
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:TODO
 */
public class CompleteResponseHandler extends SimpleChannelInboundHandler<DefaultFullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DefaultFullHttpRequest msg) throws Exception {


        HttpVersion httpVersion = msg.protocolVersion();

        System.err.println("http version:" + httpVersion.text());

        String content = new String(msg.content().array(), CharsetUtil.UTF_8);
        System.err.println("from client data:" + content);


        final FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.EMPTY_BUFFER);

        response.headers().set("custom-response-header", "Some value");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);


    }


}

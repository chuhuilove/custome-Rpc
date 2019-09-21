package com.chuhui.cuatomerpc.protocol.http.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.netty.handler.codec.http.cors.CorsConfigBuilder;
import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * HttpCorsServerInitializer
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:TODO
 */
public class HttpCorsServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        CorsConfig corsConfig = CorsConfigBuilder.forAnyOrigin().allowNullOrigin().allowCredentials().build();

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("out httpResponseEncoder",new HttpResponseEncoder());
        pipeline.addLast("in httpRequestDecoder",new HttpRequestDecoder());
        pipeline.addLast("in httpObjectAggregator",new HttpObjectAggregator(65536));
        pipeline.addLast("in out chunkedWriteHandler",new ChunkedWriteHandler());
        pipeline.addLast("in out corsHandler",new CorsHandler(corsConfig));
        pipeline.addLast(new CompleteResponseHandler());

    }
}

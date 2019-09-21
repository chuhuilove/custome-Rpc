package com.chuhui.cuatomerpc.protocol.http.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * HttpServer
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:  使用 Netty编写一个Http服务器
 */
public class HttpServer {

    /**
     * // TODO 2019年9月21日21:45:16
     * // TODO 原本计划使用Netty创建一个Http服务器,暂时没搞定.先使用内嵌的Tomcat吧
     */


    //
    // 或者通过Netty自己实现一个Http服务器


    // 通过Netty实现自己的httpserver


    static final int HTTP_SERVER_PORT = 8082;


    /**
     * 通过Netty实现自定义的Http服务器
     */
    public void start() {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpCorsServerInitializer());

            Channel channel = b.bind(HTTP_SERVER_PORT).sync().channel();
            System.out.println(" the http server has started, port:"+HTTP_SERVER_PORT);
            channel.closeFuture().sync();



        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}

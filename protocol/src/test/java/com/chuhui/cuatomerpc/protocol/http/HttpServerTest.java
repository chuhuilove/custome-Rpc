package com.chuhui.cuatomerpc.protocol.http;

import com.chuhui.cuatomerpc.protocol.http.netty.HttpServer;
import org.junit.Test;

public class HttpServerTest {

    @Test
    public void start() {

        HttpServer server = new HttpServer();
        server.start();

    }
}

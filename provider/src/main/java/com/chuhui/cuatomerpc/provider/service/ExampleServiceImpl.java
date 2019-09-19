package com.chuhui.cuatomerpc.provider.service;

import com.chuhui.customerpc.interfaces.ExampleInterface;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ExampleServiceImpl
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:TODO
 */
public class ExampleServiceImpl implements ExampleInterface {

    @Override
    public String sayHello(String name) {

        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));

        String result = MessageFormat.format("Hello:{0},current time:{1},welcome to the word of RPC", name, currentTime);
        return result;
    }
}

package com.chuhui.customerpc.register;

import java.util.HashMap;
import java.util.Map;

/**
 * LocalRegister
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:TODO
 */
public class LocalRegister {

    //通过什么来访问
    // 那张图


    private static final Map<String, Object> map = new HashMap<>();

    public static void register(String serviceName, Object clazz) {
        map.put(serviceName, clazz);
    }

}

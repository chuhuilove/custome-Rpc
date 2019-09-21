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

    private static final Map<String, Class> map = new HashMap<>();

    public static void register(String interfaceName, Class clazz) {
        map.put(interfaceName, clazz);
    }

    public static  Class get (String interfaceName){
        return map.get(interfaceName);
    }

}

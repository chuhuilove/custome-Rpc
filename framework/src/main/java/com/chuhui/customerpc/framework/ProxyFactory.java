package com.chuhui.customerpc.framework;

import com.chuhui.customerpc.convention.transfer.URL;
import com.chuhui.customerpc.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyFactory
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/9/21
 */
public class ProxyFactory {

    public static <T> T getProxy(final Class interfaceClass) {

        Object o = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                HttpClient httpClient = new HttpClient();

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                // 随机从远端获取URL,然后进行调用
                URL url = RemoteRegister.randomGetURL(interfaceClass.getName());

                String send = httpClient.send(url.getHostNmae(), url.getPort(), invocation);
                return send;
            }
        });

        return (T) o;

    }


}

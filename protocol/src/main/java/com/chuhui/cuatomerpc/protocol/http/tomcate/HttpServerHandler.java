package com.chuhui.cuatomerpc.protocol.http.tomcate;

import com.chuhui.customerpc.framework.Invocation;
import com.chuhui.customerpc.register.LocalRegister;
import io.netty.util.CharsetUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * HttpServerHandler
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/9/21
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
        try {

            Invocation invocation = (Invocation) ois.readObject();

            Class impl = LocalRegister.get(invocation.getInterfaceName());

            Method method = impl.getMethod(invocation.getMethodName(), invocation.getParamTypes());

            String invoke = (String) method.invoke(impl.newInstance(), invocation.getParams());


            IOUtils.write(invoke,resp.getOutputStream(), CharsetUtil.UTF_8);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}

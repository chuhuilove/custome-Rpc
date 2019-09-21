package com.chuhui.customerpc.framework;

import io.netty.util.CharsetUtil;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HttpClient
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/9/21
 */
public class HttpClient {


    public String send(String hostName, int port, Invocation invocation) {


        // 启动客户端,将数据发送发送到服务端


        try {
            URL url = new URL("http", hostName, port, "/");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);


            ObjectOutputStream os = new ObjectOutputStream(urlConnection.getOutputStream());
            os.writeObject(invocation);
            os.flush();
            os.close();

            String res = IOUtils.toString(urlConnection.getInputStream(), CharsetUtil.UTF_8);
            return res;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

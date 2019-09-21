package com.chuhui.customerpc.register;

import com.chuhui.customerpc.convention.Constants;
import com.chuhui.customerpc.convention.transfer.URL;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.chuhui.customerpc.convention.Constants.CONNECT_ZOOKEEPER_STRING;


/**
 * RemoteRegister
 *
 * @author: cyzi
 * @Date: 2019/9/20 0020
 * @Description:TODO
 */
public class RemoteRegister {

    static final byte DATA_SEPARATOR = 44;

    static Map<String, List<URL>> REGISTER = new HashMap<>();
    private static CuratorFramework client;

    static {

        /**
         * 静态代码块中,启动zookeeper客户端
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client =
                CuratorFrameworkFactory.builder()
                        .connectString(CONNECT_ZOOKEEPER_STRING)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .namespace(Constants.NAME_SPACE)
                        .build();
        client.start();
    }


    public static void register(String interfaceName, URL url) {

        final String newInterfaceName = "/" + interfaceName;

        // 本地保存一份
        List<URL> urls = REGISTER.get(newInterfaceName);
        if (CollectionUtils.isEmpty(urls)) {
            urls = new ArrayList<>();
            REGISTER.put(newInterfaceName, urls);
        }
        urls.add(url);

        try {
            Stat stat = client.checkExists().forPath(newInterfaceName);
            if (stat == null) {
                client.create().forPath(newInterfaceName);
            }

            client.setData().forPath(newInterfaceName, listToByteArr(urls));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<URL> getUrByInterfaceName(String interfaceName) {
        final String finalInterfaceName = "/" + interfaceName;

        try {
            byte[] bytes = client.getData().forPath(finalInterfaceName);

            List<URL> result = new ArrayList<>();

            for (int i = 0, j = 0; i < bytes.length; i++) {

                if (bytes[i] == DATA_SEPARATOR) {

                    byte[] rangeArr = Arrays.copyOfRange(bytes, j, i);
                    j = i + 1;
                    URL url = URL.parseFrom(rangeArr);
                    result.add(url);
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 负载均衡算法的实现
     * @param interfaceName
     * @return
     */
    public static URL randomGetURL(String interfaceName) {
        List<URL> urByInterfaceName = getUrByInterfaceName(interfaceName);
        return urByInterfaceName.get(ThreadLocalRandom.current().nextInt(urByInterfaceName.size()));
    }




    /**
     * 将URL转换成byte[],并且在每一个url中,添加一个",",最后所有byte[]组合成一个byte[],返回
     * 这算法,垃圾到家了..
     * //TODO 垃圾到家的算法,操
     *
     * @param urls
     * @return
     */
    private static byte[] listToByteArr(List<URL> urls) {

        List<Byte> finalByte = new ArrayList<>();
        for (URL url : urls) {

            for (byte by : url.toByteArray()) {
                finalByte.add(by);
            }
            finalByte.add(DATA_SEPARATOR);
        }

        byte[] finalByteArr = new byte[finalByte.size()];

        for (int i = 0; i < finalByte.size(); i++) {
            finalByteArr[i] = finalByte.get(i);
        }
        return finalByteArr;
    }

}

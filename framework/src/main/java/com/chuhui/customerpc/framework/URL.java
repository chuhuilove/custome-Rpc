package com.chuhui.customerpc.framework;

/**
 * framework
 *
 * @author: cyzi
 * @Date: 2019/9/20 0020
 * @Description:TODO
 */
public class URL {
    private String hostName;
    private Integer port;


    public URL(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}

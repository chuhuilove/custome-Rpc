package com.chuhui.cuatomerpc.protocol.http.tomcate;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

import static com.chuhui.customerpc.convention.Constants.PROVIDER_HOSTNAME;
import static com.chuhui.customerpc.convention.Constants.PROVIODER_HOSTNAME;

/**
 * HttpServer
 * <p>
 * 启动内嵌tomcat作为http服务器
 *
 * @author: 纯阳子
 * @date: 2019/9/21
 */
public class HttpServer {


    public void start() {

        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(PROVIDER_HOSTNAME);

        Engine standardEngine = new StandardEngine();
        standardEngine.setDefaultHost(PROVIODER_HOSTNAME);

        Host host = new StandardHost();
        host.setName(PROVIODER_HOSTNAME);


        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());


        host.addChild(context);
        standardEngine.addChild(host);

        service.setContainer(standardEngine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }


    }


}

import com.chuhui.cuatomerpc.protocol.http.tomcate.HttpServer;
import com.chuhui.cuatomerpc.provider.service.ExampleServiceImpl;
import com.chuhui.customerpc.convention.transfer.URL;
import com.chuhui.customerpc.interfaces.ExampleInterface;
import com.chuhui.customerpc.register.LocalRegister;
import com.chuhui.customerpc.register.RemoteRegister;

import static com.chuhui.customerpc.convention.Constants.PROVIDER_HOSTNAME;
import static com.chuhui.customerpc.convention.Constants.PROVIODER_HOSTNAME;

/**
 * Framework
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:TODO
 */
public class Provider {


    public static void main(String[] args) {


        // 1. 本地注册

        LocalRegister.register(ExampleInterface.class.getName(), ExampleServiceImpl.class);

        // 2. 远端注册

        URL build = URL.newBuilder().setHostNmae(PROVIODER_HOSTNAME).setPort(PROVIDER_HOSTNAME).build();
        RemoteRegister.register(ExampleInterface.class.getName(), build);


        // 3. 启动服务
        HttpServer server = new HttpServer();
        server.start();
    }

}

import com.chuhui.cuatomerpc.protocol.http.HttpServer;
import com.chuhui.cuatomerpc.provider.service.ExampleServiceImpl;
import com.chuhui.customerpc.interfaces.ExampleInterface;
import com.chuhui.customerpc.register.LocalRegister;

import java.time.LocalDateTime;

/**
 * Framework
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:TODO
 */
public class Provider {


    public static void main(String[] args) {


        ExampleInterface exampleService=new ExampleServiceImpl();


        // 1. 本地注册

        LocalRegister.register("exampleService",exampleService);


//        exampleService


        // 2. 远端注册


        HttpServer server = new HttpServer();
        server.start();
    }

}

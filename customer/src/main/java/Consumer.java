import com.chuhui.customerpc.framework.Invocation;
import com.chuhui.customerpc.framework.ProxyFactory;
import com.chuhui.customerpc.interfaces.ExampleInterface;

/**
 * Cuatomer
 *
 * @author: cyzi
 * @Date: 2019/9/19 0019
 * @Description:TODO
 */
public class Consumer {


    public static void main(String[] args) {

        ExampleInterface exampleImple = ProxyFactory.getProxy(ExampleInterface.class);
        System.err.println(exampleImple.sayHello("cyzi"));

    }


}

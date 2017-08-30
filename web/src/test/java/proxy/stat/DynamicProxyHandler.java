package proxy.stat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by qq136 on 2017/8/8.
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Target target;

    public DynamicProxyHandler(Target target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("========before==========");
        Object result = method.invoke(target,args);
        System.out.println("========after===========");
        return result;
    }
}

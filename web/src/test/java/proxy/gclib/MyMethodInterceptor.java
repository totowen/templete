package proxy.gclib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by qq136 on 2017/8/8.
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>>MethodInterceptor start..."+o.getClass().getSimpleName());
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println(">>>>MethodInterceptor ending...");
        return "result";
    }
}

package proxy.stat;


/**
 * Created by qq136 on 2017/8/8.
 */
public class ProxyTest {

    public static void main(String[] args) {

//        staticProxy();//静态代理

//        dynamicProxy();//动态代理



    }

    /**
     * 动态代理主要是通过反射机制，在运行时动态生成所需代理的class.
     */
    private static void dynamicProxy() {
        Target target = new TargetImpl();
        DynamicProxyHandler handler = new DynamicProxyHandler(target);
        Target proxySubject = (Target) java.lang.reflect.Proxy.newProxyInstance(TargetImpl.class.getClassLoader(),TargetImpl.class.getInterfaces(),handler);
        String result = proxySubject.execute();
        System.out.println(result);
    }


    /**
     * 静态代理其实就是在程序运行之前，提前写好被代理方法的代理类，编译后运行。在程序运行之前，class已经存在。
     */
    private static void staticProxy() {
        TargetImpl target = new TargetImpl();
        Proxy proxy = new Proxy(target);
        System.out.println(proxy.execute());
    }

}

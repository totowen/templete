package proxy.gclib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * CGLib采用了非常底层的字节码技术，
 * 其原理是通过字节码技术为一个类创建子类，
 * 并在子类中采用方法拦截的技术拦截所有父类方法的调用，
 * 顺势织入横切逻辑。
 * JDK动态代理与CGLib动态代理均是实现Spring AOP的基础。
 * Created by qq136 on 2017/8/8.
 */
public class CglibTest {

    public static void  main(String ... args) {
        System.out.println("***************");
        CglibTest test = new CglibTest();
        Target proxyTarget = (Target) test.createProxy(Target.class);
        String res = proxyTarget.execute();
        System.out.println(res);
    }

    /**
     * 代理对象的生成过程由Enhancer类实现，大概步骤如下：
     1、生成代理类Class的二进制字节码；
     2、通过Class.forName加载二进制字节码，生成Class对象；
     3、通过反射机制获取实例构造，并初始化代理类对象。
     * @param targetClass
     * @return
     */
    public Object createProxy(Class targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MyMethodInterceptor());
        return enhancer.create();
    }

}

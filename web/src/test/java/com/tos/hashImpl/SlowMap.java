package com.tos.hashImpl;




/**
 * @author qq136
 * @date 2017/11/6.
 */
public class SlowMap {

    public synchronized void doSomething(){
        System.out.println("super.doSomething");
    }


}

class B extends SlowMap{

    public synchronized  void doSomething(){
        System.out.println("this.doSomething");
        super.doSomething();
    }

    public static void main(String[] args) {
        B b = new B();
        b.doSomething();
    }

}

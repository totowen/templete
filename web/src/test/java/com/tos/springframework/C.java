package com.tos.springframework;

/**
 * Created by qq136 on 2017/8/8.
 */
public class C implements B {
    @Override
    public String read() {
        return "123";
    }

    @Override
    public String write() {
        return "abc";
    }
}

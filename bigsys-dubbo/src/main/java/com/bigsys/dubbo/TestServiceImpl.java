package com.bigsys.dubbo;

public class TestServiceImpl implements TestService{
    @Override
    public String sayHello() {
        return "hello world";
    }
}

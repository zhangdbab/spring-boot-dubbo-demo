package com.sans.provider;

import com.sans.base.service.IHelloService;
//import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

//@Service
@DubboService
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello() {
        return "hello world!";
    }
}

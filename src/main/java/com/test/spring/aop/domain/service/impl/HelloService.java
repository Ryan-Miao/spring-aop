package com.test.spring.aop.domain.service.impl;

import com.test.spring.aop.domain.service.IHelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by miaorf on 2016/7/16.
 */
@Service
public class HelloService implements IHelloService {

    @Value("${name}")
    private String name;

    public void sayHi() {
        for (int i = 0; i < 100000; i++) {

        }
        System.out.println("Hi "+name);
        System.out.println("I'm  running.");
    }
}

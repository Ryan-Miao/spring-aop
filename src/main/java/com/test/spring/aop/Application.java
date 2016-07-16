package com.test.spring.aop;

import com.test.spring.aop.domain.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by miaorf on 2016/7/16.
 */
@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    IHelloService helloService;

    public void run(String... strings) throws Exception {
        System.out.println("start application by CommandLineRunner, the args:"+strings.length);
        helloService.sayHi();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}

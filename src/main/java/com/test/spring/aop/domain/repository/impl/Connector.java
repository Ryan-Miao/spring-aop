package com.test.spring.aop.domain.repository.impl;

import com.test.spring.aop.domain.repository.IConnector;
import org.springframework.stereotype.Component;

/**
 * Created by miaorf on 2016/7/16.
 */
@Component
public class Connector implements IConnector {

    public Object getSthFromRemote() {
        //mock time
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        return null;
    }
}

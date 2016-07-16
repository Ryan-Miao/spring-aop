package com.test.spring.aop.domain.service.impl;

import com.test.spring.aop.domain.entiry.User;
import com.test.spring.aop.domain.repository.IConnector;
import com.test.spring.aop.domain.repository.IUserDao;
import com.test.spring.aop.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by miaorf on 2016/7/16.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IConnector connector;

    public List<User> getAll() {
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        connector.getSthFromRemote();
        return userDao.getAll();
    }
}

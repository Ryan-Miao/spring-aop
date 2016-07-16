package com.test.spring.aop.domain.repository.impl;

import com.test.spring.aop.domain.entiry.User;
import org.aspectj.lang.annotation.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by miaorf on 2016/7/16.
 */
public class UserDaoTest {
    private static UserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception{
        userDao = new UserDao();
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = userDao.getAll();
        assertTrue(all.size()==10000);
    }

}
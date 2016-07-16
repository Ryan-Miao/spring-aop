package com.test.spring.aop.domain.repository;

import com.test.spring.aop.domain.entiry.User;

import java.util.List;

/**
 * Created by miaorf on 2016/7/16.
 */
public interface IUserDao {

    List<User> getAll();
}

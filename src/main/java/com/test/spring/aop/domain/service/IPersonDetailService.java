package com.test.spring.aop.domain.service;

import com.test.spring.aop.domain.entiry.Person;
import com.test.spring.aop.domain.exception.InvalidParamException;

/**
 * Service to get the person's detail
 * Created by pact on 2017/3/9.
 */
public interface IPersonDetailService {

    Person getDetailByName(String name);
    Person getPersonById(String id);
}

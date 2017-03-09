package com.test.spring.aop.domain.repository;

import com.test.spring.aop.domain.entiry.Person;
import com.test.spring.aop.domain.exception.InvalidParamException;
import com.test.spring.aop.monitor.PersonNotNull;

/**
 * Created by pact on 2017/3/9.
 */
public interface IPersonDetailConnector {

//    @PersonNotNull(responseClass = Person.class)
    Person getDetailByName(String name) throws InvalidParamException;

}

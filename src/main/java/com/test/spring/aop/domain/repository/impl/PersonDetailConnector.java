package com.test.spring.aop.domain.repository.impl;

import com.test.spring.aop.domain.entiry.Person;
import com.test.spring.aop.domain.exception.InvalidParamException;
import com.test.spring.aop.domain.repository.IPersonDetailConnector;
import com.test.spring.aop.monitor.PersonNotNull;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by pact on 2017/3/9.
 */
@Repository
public class PersonDetailConnector implements IPersonDetailConnector {

    @PersonNotNull(responseClass = Person.class)
    public Person getDetailByName(String name) throws InvalidParamException {
        if (StringUtils.isEmpty(name)){
            throw new InvalidParamException("name can't be empty.");
        }
        Person person  = new Person(name);
        return null;
    }
}

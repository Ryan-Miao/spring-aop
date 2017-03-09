package com.test.spring.aop.domain.service.impl;

import com.test.spring.aop.domain.entiry.Person;
import com.test.spring.aop.domain.exception.InvalidParamException;
import com.test.spring.aop.domain.repository.DeprecatedPersonDetailConnector;
import com.test.spring.aop.domain.repository.IPersonDetailConnector;
import com.test.spring.aop.domain.service.IPersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pact on 2017/3/9.
 */
@Service
public class PersonDetailService implements IPersonDetailService{

    @Autowired
    private IPersonDetailConnector personDetailConnector;

    @Autowired
    private DeprecatedPersonDetailConnector deprecatedPersonDetailConnector;

    @Override
    public Person getDetailByName(String name) {

        try {
            return personDetailConnector.getDetailByName(name);
        } catch (InvalidParamException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Person getPersonById(String id) {
        try {
            return deprecatedPersonDetailConnector.getPersonById(id);
        } catch (InvalidParamException e) {
            e.printStackTrace();
        }
        return null;
    }

}

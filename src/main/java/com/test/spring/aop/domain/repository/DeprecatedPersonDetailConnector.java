package com.test.spring.aop.domain.repository;

import com.test.spring.aop.domain.entiry.Person;
import com.test.spring.aop.domain.exception.InvalidParamException;
import com.test.spring.aop.monitor.PersonNotNull;
import org.springframework.stereotype.Repository;

/**
 * The old version's person detail connector when we didn't use interface-oriented.
 * Created by pact on 2017/3/9.
 */
@PersonNotNull
@Repository("deprecatedPersonDetailConnector")
public class DeprecatedPersonDetailConnector {

    public Person getPersonById(String id) throws InvalidParamException {
        throw new InvalidParamException("The id is not support right now.");
    }
}

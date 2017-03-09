package com.test.spring.aop.domain.exception;

/**
 * Created by pact on 2017/3/9.
 */
public class InvalidParamException extends Exception {
    public InvalidParamException() {
        super();
    }

    public InvalidParamException(String message) {
        super(message);
    }
}

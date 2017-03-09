package com.test.spring.aop.monitor;

import com.test.spring.aop.domain.entiry.Person;
import com.test.spring.aop.domain.exception.InvalidParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by pact on 2017/3/9.
 */
@Aspect
@Component
public class PersonMonitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonMonitor.class);

    @Pointcut("@annotation(com.test.spring.aop.monitor.PersonNotNull) " +
            "|| @within(com.test.spring.aop.monitor.PersonNotNull)")
    public void connectorAspect() {}

    @AfterThrowing(pointcut = "connectorAspect()", throwing = "exception")
    public void doAfterConnectorThrowing(JoinPoint joinPoint, Throwable exception){
        //request parameters
        Object[] args = joinPoint.getArgs();

        //get exception
        Class<? extends Throwable> exceptionClass = exception.getClass();
        if (exception instanceof InvalidParamException){
            InvalidParamException invalidParamException = (InvalidParamException) exception;
            //exception message
            String message = invalidParamException.getMessage();
            LOGGER.error("Get a exception: {}", message);
        }
    }

    @Around("connectorAspect()")
    public Object interceptConnectorException(ProceedingJoinPoint joinPoint) throws Throwable {
        //The connector class name
        Class<?> targetClass = joinPoint.getTarget().getClass();
        //The parameters
        Object[] arguments = joinPoint.getArgs();
        //The method return type
        Class returnType = null;

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        returnType = methodSignature.getReturnType();
        Object returnVal = null;
        try {
            returnVal = joinPoint.proceed();
        } catch (Throwable exception) {
            if (exception instanceof InvalidParamException){
                InvalidParamException invalidParamException = (InvalidParamException) exception;
                //exception message
                String message = invalidParamException.getMessage();
                LOGGER.error("Get a exception: {}", message);

                //get the constructor we want to new
                try {
                    Constructor constructor = returnType.getConstructor(Object.class);
                    if (constructor!=null){
                        returnVal = constructor.newInstance("Created by Aop: "+message);
                    }
                } catch (NoSuchMethodException e) {
                    returnVal = new Person("The constructor not exist.");
                } catch (SecurityException e) {
                    returnVal = new Person("Security forbidden.");
                }


            }else {
                throw exception;
            }
        }
        return returnVal;
    }

}

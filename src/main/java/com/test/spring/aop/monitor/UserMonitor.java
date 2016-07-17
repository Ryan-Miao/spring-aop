package com.test.spring.aop.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by miaorf on 2016/7/16.
 */
@Aspect
@Component
public class UserMonitor {

    private static Logger logger = LoggerFactory.getLogger(UserMonitor.class);

    @Pointcut("execution(* com.test.spring.aop.web.UserController.users())")
    public void controllerPerformance() {
    }

    @Pointcut("execution(* com.test.spring.aop.domain.service.impl.UserService.getAll())")
    public void servicePerformance() {
    }

    @Pointcut("execution(* com.test.spring.aop.domain.repository.impl.UserDao.getAll())")
    public void repositoryPerformance() {
    }

    @Pointcut("execution(* com.test.spring.aop.domain.repository.impl.Connector.getSthFromRemote())")
    public void connectorPerformance() {
    }


    @Around("controllerPerformance() || servicePerformance() || repositoryPerformance() || connectorPerformance()")
    public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        logger.info("=================start {}=====================",signature.getName());

        StopWatch stopWatch = new StopWatch("controller");
        try {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }

            stopWatch.start(signature.toString());
        } catch (IllegalStateException e) {
            logger.error("watch start error:", e);
        }

        Object proceed = joinPoint.proceed();

        try {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
                logger.info(stopWatch.prettyPrint()+"\n================= end {}=====================",signature.getName());
            }
        } catch (IllegalStateException e) {
            logger.error("watch end error:", e);
        }

        return proceed;
    }

}

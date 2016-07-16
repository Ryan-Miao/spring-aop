package com.test.spring.aop.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by miaorf on 2016/7/16.
 */
@Aspect
@Component
public class UserMonitor2 {

    private static Logger logger = LoggerFactory.getLogger(UserMonitor2.class);
    StopWatch controllerWatch;
    StopWatch serviceWatch;
    StopWatch repositoryWatch;

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

    @Before("controllerPerformance()")
    public void startWatch() {
        controllerWatch = new StopWatch("controller");
        serviceWatch = new StopWatch("service");
        repositoryWatch= new StopWatch("repository");
    }

    @After("controllerPerformance()")
    public void endWatch() {
        //记录各自耗时统计
        logger.info(controllerWatch.prettyPrint());
        logger.info(serviceWatch.prettyPrint());
        logger.info(repositoryWatch.prettyPrint());

        //统计百分比
        List list = new ArrayList();

        long repositoryWatchTotalTimeMillis = repositoryWatch.getTotalTimeMillis();

        long controllerWatchTotalTimeMillis = controllerWatch.getTotalTimeMillis();
        StopWatch.TaskInfo[] serviceWatchTaskInfo = serviceWatch.getTaskInfo();
        long serviceWatchTotalTimeMillis = serviceWatch.getTotalTimeMillis();
        Map<String,Object> map = new HashMap<String,Object>();
        for (StopWatch.TaskInfo taskInfo : serviceWatchTaskInfo) {
            long timeMillis = taskInfo.getTimeMillis();
            map.put(taskInfo.getTaskName(),timeMillis/(double)controllerWatchTotalTimeMillis*100);
        }

        //销毁
        controllerWatch = null;
        serviceWatch = null;
        repositoryWatch = null;
    }

    @Around("servicePerformance() || repositoryPerformance() || connectorPerformance()")
    public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("---------------------------");
        try {
            //如果是一层一层的，这里只能统计到到下一层需要的时间，因为返回值后没有统计，也就是说只能统计平行的调用
            if (stopWatch.isRunning()){
                stopWatch.stop();
            }
            stopWatch.start(joinPoint.getSignature().toString());
        } catch (IllegalStateException e) {
            logger.error("watch start error:",e);
        }

        Object proceed = joinPoint.proceed();

        try {
            if (stopWatch.isRunning()){
                stopWatch.stop();
            }
        } catch (IllegalStateException e) {
            logger.error("watch end error:",e);
        }

        return proceed;

    }

}

package com.test.spring.aop.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by miaorf on 2016/7/16.
 */
@Aspect
@Component
public class ServiceMonitor {

    private static Logger logger = LoggerFactory.getLogger(ServiceMonitor.class);

    StopWatch stopWatch = new StopWatch("performance");

    /**
     * define point
     */
    @Pointcut("execution(* com.test.spring.aop..*Service.*(..))")
    public void performance(){}

//    @Before("performance()")
//    public void startService(){
//        System.out.println("-----------service start-------");
//    }
//    @After("performance()")
//    public void endService(){
//        System.out.println("-----------service end neither success or failed -------");
//    }
//
//    @AfterReturning("performance()")
//    public void logServiceAccess(JoinPoint joinPoint) {
//        System.out.println("service completed:"+joinPoint);
//    }

    /**
     * 必须要返回，不然后面收不到
     * @param point
     * @return
     */
    @Around("performance()")
    public Object watchPerformance(ProceedingJoinPoint point){
        System.out.println("The service start:");

        stopWatch.start(point.getSignature().toString());
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            logger.error("The service not work!",throwable);
            throwable.printStackTrace();
        }finally {
            stopWatch.stop();
            stopWatch.start("b");
            for (int i = 0; i < 12345; i++) {

            }
            stopWatch.stop();
            StopWatch.TaskInfo[] taskInfo = stopWatch.getTaskInfo();
            for (StopWatch.TaskInfo info : taskInfo) {
                System.out.println(info.getTaskName());
                System.out.println(info.getTimeMillis());
            }
            logger.info("The {} run time:{}",point.getSignature().toString(),stopWatch.prettyPrint());
        }

        return null;
    }
}

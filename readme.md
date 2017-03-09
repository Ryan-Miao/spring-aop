spring-aop
=============
The project is the practice of learning **spring-aop**.

[TOC]
### Second Time try -- Customized Annotation    

1. customized annotaion
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
```
2. pointcut

method:
```java
@Pointcut("@annotation(com.test.spring.aop.monitor.PersonNotNull)")
```

class:
```java
@Pointcut("@annotation(com.test.spring.aop.monitor.PersonNotNull) || @within(com.test.spring.aop.monitor.PersonNotNull)")
public void connectorAspect() {}
```


3. get return type
```java
Class returnType = null;
MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
returnType = methodSignature.getReturnType();
```

4. get annotation value   

Since  we use the object-oriented and interface-oriented design pattern. The child instance would 
be reated as their father class type. Thus we only find the father's variable and annotation 
or method, etc.  

That is, we must add annotation to the child to get the aspect, and if we need get the annotation 
value, we have find it in the father's. So the father must have the same annotation  with their 
children, just for recognize but not for aspect.

5. reflect to new instance
```java
 Constructor constructor = returnType.getConstructor(String.class);
 returnVal = constructor.newInstance("Created by Aop");
```


### First Time try -- Hello world
文档：[Spring-AOP实践 - 统计访问时间](http://www.cnblogs.com/woshimrf/p/5677337.html)    
项目运行：
```
mvn spring-boot:run
```
访问测试：
```
localhost:8080/user/all
```

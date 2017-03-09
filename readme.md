spring-aop
=============
The project is the practice of learning **spring-aop**.

## How to run
```
mvn spring-boot:run
```

```
http://localhost:8080/ann/method
http://localhost:8080/ann/class
```

### Second Time try -- Customized Annotation

#### BackGround

Our project exchange message with **Feign**, one powerful http client. Feign would
treat the httpStatus code 200+ as error and then throw a hystrixRuntimeException. 
However, we use 500 to show failed status and send the error message for more details.
We want get the http response body to get the message, but Feign just throw exception.
How bat it is. So we should capture the exception and get the body, then do something 
with the body. Thus, I want to use spring aop to do this.

#### Challenge

##### 1. Customized Annotation
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PersonNotNull {
    Class<?> responseClass() default Object.class;
}
```
This is the first time I create an annotation to use in the practice. There are some 
basic information I should collect.  
- `@Target` mark where the annotation should sit.
- `@Retention` show when the  annotaion effective.

##### 2. pointcut/expression

In the beginning, I want the annotation locate on a method. But prefer class later.
```java
@Pointcut("@annotation(com.test.spring.aop.monitor.PersonNotNull) " +
            "|| @within(com.test.spring.aop.monitor.PersonNotNull)")
public void connectorAspect() {}
```
- `@annotation` will find the method, do not make mistakes on spelling.
- `@within` will find the class, all of the methods under the class.


##### 3. get the return type
Since I need do something to create an error response. I need get the return type.
```java
MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
Class returnType = methodSignature.getReturnType();
```

##### 4. get the annotation value   
Since  we use the object-oriented and interface-oriented design pattern. The child instance would 
be treated as their father class type. Thus we only find the father's variable and annotation 
or method, etc.  

That is, we must add annotation to the child to get the aspect, and if we need get the annotation 
value, we have to find it in the father's. So the father must have the same annotation  with their 
children, just for recognize but not for aspect.

Finally, I found I could just get the return type from the method, do not need to get the response 
type from the annotation. But I still make me happy that found the conclusion above.

##### 5. reflect to new instance
```java
 Constructor constructor = null;
try {
    constructor = returnType.getConstructor(Object.class);
    if (constructor!=null){
        returnVal = constructor.newInstance("Created by Aop: "+message);
    }
} catch (NoSuchMethodException e) {
    returnVal = new Person("The constructor not exist.");
} catch (SecurityException e) {
    returnVal = new Person("Security forbidden.");
}

```



### First Time try -- Hello world
文档：[Spring-AOP实践 - 统计访问时间](http://www.cnblogs.com/woshimrf/p/5677337.html)    


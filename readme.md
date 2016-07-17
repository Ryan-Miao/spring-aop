spring-aop
=============
本测试项目主要用来测试aop的使用，任务是记录特定访问时间，
进而分析究竟哪里耗时，从而进行优化。
    
   
文档：[Spring-AOP实践 - 统计访问时间](http://www.cnblogs.com/woshimrf/p/5677337.html)    
项目运行：
```
mvn spring-boot:run
```
访问测试：
```
localhost:8080/user/all
```

package cn.zgy.springframework.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}

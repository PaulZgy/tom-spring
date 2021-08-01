package cn.zgy.springframework.aop;

import org.aopalliance.aop.Advice;

public interface Advisor {

    Advice getAdvice();
}

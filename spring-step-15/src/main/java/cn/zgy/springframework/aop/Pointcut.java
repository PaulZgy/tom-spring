package cn.zgy.springframework.aop;


public interface Pointcut {

    ClassFilter getClassFileter();

    MethodMatcher getMethodMatcher();
}

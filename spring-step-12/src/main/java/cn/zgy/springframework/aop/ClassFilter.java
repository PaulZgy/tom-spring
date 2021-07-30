package cn.zgy.springframework.aop;

public interface ClassFilter {

    boolean matches(Class<?> clazz);
}

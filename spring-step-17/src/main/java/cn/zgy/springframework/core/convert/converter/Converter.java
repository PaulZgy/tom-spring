package cn.zgy.springframework.core.convert.converter;

public interface Converter<S, T> {
    // 将原来的S类型的数据转为T
    T convert(S source);
}

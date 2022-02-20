package cn.zgy.springframework.core.convert.converter;

public interface ConverterFactory<S, R> {
    // 获取转换器将S转换为目标类型T，其中T也是R的一个实例
    <T extends R> Converter<S, T> getConverter(Class<T> targeType);
}

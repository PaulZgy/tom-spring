package cn.zgy.springframework.core.convert.support;

import cn.zgy.springframework.core.convert.converter.Converter;
import cn.zgy.springframework.core.convert.converter.ConverterFactory;
import com.sun.istack.internal.Nullable;
import org.springframework.util.NumberUtils;

public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targeType) {
        return null;
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}

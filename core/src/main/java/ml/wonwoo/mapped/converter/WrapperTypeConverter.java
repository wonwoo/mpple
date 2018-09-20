package ml.wonwoo.mapped.converter;

import ml.wonwoo.util.ClassUtils;

public class WrapperTypeConverter implements MappedConverter {

    @Override
    public boolean supports(Class<?> target) {
        return ClassUtils.isWrapperType(target);
    }

    @Override
    public Object convert(Class<?> clazz, Object value, Class<?> target, Object context) {
        return value;
    }
}

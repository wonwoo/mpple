package ml.wonwoo.mapped.converter;

import ml.wonwoo.util.ClassUtils;

public class WrapperTypeConverter implements MappedConverter {

    @Override
    public boolean supports(Class<?> target) {
        return !ClassUtils.isObject(target);
    }

    @Override
    public Object convert(Class<?> rootClass, Object value, Class<?> target, Object context) {
        return value;
    }
}

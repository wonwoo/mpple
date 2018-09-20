package ml.wonwoo.mapped.converter;

import java.time.temporal.Temporal;

public class MiscConverter implements MappedConverter {

    @Override
    public boolean supports(Class<?> target) {
        return Temporal.class.isAssignableFrom(target) || hook(target);
    }

    @Override
    public Object convert(Class<?> clazz, Object value, Class<?> target, Object context) {
        return value;
    }

}

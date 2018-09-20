package ml.wonwoo.mapped.converter;

public interface MappedConverter {

    default boolean supports(Class<?> target) {
        return hook(target);
    }

    default boolean hook(Class<?> target) {
        return false;
    }

    Object convert(Class<?> clazz, Object value, Class<?> target, Object context);
}

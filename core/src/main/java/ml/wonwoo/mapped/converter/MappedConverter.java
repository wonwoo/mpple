package ml.wonwoo.mapped.converter;

public interface MappedConverter {

    default boolean supports(Class<?> target) {
        return false;
    }

    Object convert(Class<?> rootClass, Object value, Class<?> target, Object context);
}

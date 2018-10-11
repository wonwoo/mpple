package ml.wonwoo.mapped.converter;

@FunctionalInterface
public interface MappedConverter {

    default boolean supports(Class<?> target) {
        return false;
    }

    Object convert(Class<?> rootClass, Object value, Class<?> target, Object context);
}

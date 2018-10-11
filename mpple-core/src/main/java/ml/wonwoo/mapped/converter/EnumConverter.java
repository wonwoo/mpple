package ml.wonwoo.mapped.converter;

public class EnumConverter implements MappedConverter {

    @Override
    public boolean supports(Class<?> target) {
        return target.isEnum();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Class<?> rootClass, Object value, Class<?> target, Object context) {
        Enum<?> enumValue = (Enum<?>) value;
        return Enum.valueOf((Class) target, enumValue.name());
    }
}

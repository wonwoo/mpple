package ml.wonwoo.mapped.converter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import ml.wonwoo.mapped.mapping.MappingInstance;
import ml.wonwoo.util.ClassUtils;
import net.jodah.typetools.TypeResolver;

public class MapConverter implements MappedConverter {

    private final MappingInstance mappingInstance;

    public MapConverter(MappingInstance mappingInstance) {
        this.mappingInstance = mappingInstance;
    }

    @Override
    public boolean supports(Class<?> target) {
        return Map.class.isAssignableFrom(target);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Class<?> rootClass, Object value, Class<?> target, Object context) {
        try {
            Method method = rootClass.getDeclaredMethod((String) context, target);
            Type genericType = ClassUtils.getGenericType(method);
            Class<?>[] type = TypeResolver.resolveRawArguments(genericType, rootClass);
            Class<?> keyType = type[0];
            Class<?> valueType = type[1];

            Map<Object, Object> destination = mapCreate(target);
            Map<Object, Object> map = (Map<Object, Object>) value;
            for (Entry<?, ?> entry : map.entrySet()) {
                Object entryKey = entry.getKey();
                Object entryValue = entry.getValue();
                if (!ClassUtils.isObject(keyType)) {
                    entryKey = mappingInstance.map(entryKey, keyType);
                }
                if (!ClassUtils.isObject(valueType)) {
                    entryValue = mappingInstance.map(entryValue, valueType);
                }
                destination.put(entryKey, entryValue);
            }
            return destination;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private Map<Object, Object> mapCreate(Class<?> clazz) {
        if (clazz.isInterface()) {
            return new HashMap<>();
        }
        return (Map<Object, Object>) ClassUtils.instantiateClass(clazz);
    }

}

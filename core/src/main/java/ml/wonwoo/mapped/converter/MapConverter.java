package ml.wonwoo.mapped.converter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import ml.wonwoo.mapped.MappingInstance;
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
    public Object convert(Class<?> clazz, Object value, Class<?> target, Object context) {
        try {
            Method method = clazz.getDeclaredMethod((String) context, target);
            Type genericType = ClassUtils.getGenericType(method);
            Class<?>[] type = TypeResolver.resolveRawArguments(genericType, clazz);
            Class<?> keyType = type[0];
            Class<?> valueType = type[1];

            Map<Object, Object> destinaion = mapCreate(target);
            Map<Object, Object> map = (Map<Object, Object>) value;
            for (Entry<?, ?> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                if (!ClassUtils.isWrapperType(keyType)) {
                    k = mappingInstance.newInstance(k, keyType);
                }
                if (!ClassUtils.isWrapperType(valueType)) {
                    v = mappingInstance.newInstance(v, valueType);
                }
                destinaion.put(k, v);
            }
            return destinaion;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    protected Map<Object, Object> mapCreate(Class<?> clazz) {
        if (clazz.isInterface()) {
            return new HashMap<>();
        }
        return (Map<Object, Object>) ClassUtils.newInstance(clazz);
    }

}

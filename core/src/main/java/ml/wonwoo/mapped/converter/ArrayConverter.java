package ml.wonwoo.mapped.converter;

import java.lang.reflect.Array;
import ml.wonwoo.mapped.MappingInstance;
import ml.wonwoo.util.ClassUtils;

public class ArrayConverter implements MappedConverter {

    private final MappingInstance mappingInstance;

    public ArrayConverter(MappingInstance mappingInstance) {
        this.mappingInstance = mappingInstance;
    }

    @Override
    public boolean supports(Class<?> target) {
        return target.isArray();
    }

    @Override
    public Object convert(Class<?> clazz, Object value, Class<?> target, Object context) {
        if (!ClassUtils.isWrapperArrayType(target)) {
            Object[] arrays = (Object[]) value;
            Object[] destination = new Object[arrays.length];
            Object obj = Array.newInstance(target.getComponentType(), arrays.length);
            for (int i = 0; i < arrays.length; i++) {
                destination[i] = mappingInstance.newInstance(arrays[i], target.getComponentType());
            }
            System.arraycopy(destination, 0, obj, 0, arrays.length);
            return obj;
        }
        return value;
    }
}
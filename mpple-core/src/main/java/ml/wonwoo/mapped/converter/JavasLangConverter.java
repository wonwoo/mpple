package ml.wonwoo.mapped.converter;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import ml.wonwoo.mapped.mapping.MappingInstance;
import ml.wonwoo.util.ClassUtils;
import net.jodah.typetools.TypeResolver;

public class JavasLangConverter implements MappedConverter {

    private final MappingInstance mappingInstance;

    public JavasLangConverter(MappingInstance mappingInstance) {
        this.mappingInstance = mappingInstance;
    }

    @Override
    public boolean supports(Class<?> target) {
        return Seq.class.isAssignableFrom(target);
    }

    @SuppressWarnings("unchecked")
    protected <T> Seq<T> collectionCreate(Class<T> clazz) {
        if (clazz.isInterface()) {
            return List.Nil.instance();
        } else {
            return (Seq<T>) ClassUtils.instantiateClass(clazz);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Class<?> rootClass, Object value, Class<?> target, Object context) {
        try {
            Method method = rootClass.getDeclaredMethod((String) context, target);
            Type genericType = ClassUtils.getGenericType(method);
            Class<?> type = TypeResolver.resolveRawArgument(genericType, rootClass);
            Seq<Object> collection = collectionCreate((Class<Object>) target);
            Seq<Object> list = (Seq) value;
            for (Object obj : list) {
                if (ClassUtils.isObject(type)) {
                    collection = collection.append(mappingInstance.map(obj, type));
                } else {
                    collection = collection.append(obj);
                }
            }
            return collection;
        } catch (Exception e) {
            return null;
        }
    }
}

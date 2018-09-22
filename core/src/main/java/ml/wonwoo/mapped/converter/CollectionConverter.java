package ml.wonwoo.mapped.converter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import ml.wonwoo.mapped.mapping.MappingInstance;
import ml.wonwoo.util.ClassUtils;
import net.jodah.typetools.TypeResolver;

public class CollectionConverter implements MappedConverter {

    private final MappingInstance mappingInstance;

    public CollectionConverter(MappingInstance mappingInstance) {
        this.mappingInstance = mappingInstance;
    }

    @Override
    public boolean supports(Class<?> target) {
        return Collection.class.isAssignableFrom(target);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Class<?> rootClass, Object value, Class<?> target, Object context) {
        try {
            Method method = rootClass.getDeclaredMethod((String) context, target);
            Type genericType = ClassUtils.getGenericType(method);
            Class<?> type = TypeResolver.resolveRawArgument(genericType, rootClass);
            Collection<Object> collection = collectionCreate((Class<Object>) target);
            List<Object> list = (List) value;
            for (Object obj : list) {
                if (!ClassUtils.isObject(type)) {
                    collection.add(obj);
                } else {
                    collection.add(mappingInstance.map(obj, type));
                }
            }
            return collection;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    protected static <T> Collection<T> collectionCreate(Class<T> clazz) {
        if (clazz.isInterface()) {
            if (Set.class.isAssignableFrom(clazz)) {
                return new HashSet<>();
            } else if (SortedSet.class.isAssignableFrom(clazz)) {
                return new TreeSet<>();
            } else {
                return new ArrayList<>();
            }
        } else {
            return (Collection<T>) ClassUtils.instantiateClass(clazz);
        }
    }

}

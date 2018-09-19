package ml.wonwoo.util;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ClassUtils {

    private static final Map<Class<?>, Class<?>> wrapperType;

    static {
        final Map<Class<?>, Class<?>> wrapper = new ConcurrentHashMap<>();
        wrapper.put(Boolean.class, boolean.class);
        wrapper.put(Byte.class, byte.class);
        wrapper.put(Character.class, char.class);
        wrapper.put(Short.class, short.class);
        wrapper.put(Integer.class, int.class);
        wrapper.put(Long.class, long.class);
        wrapper.put(Float.class, float.class);
        wrapper.put(Double.class, double.class);
        wrapper.put(String.class, String.class);
        wrapperType = Collections.unmodifiableMap(wrapper);
    }

    public static boolean isWrapperType(Class<?> type) {
        return wrapperType.containsKey(type) || wrapperType.containsValue(type);
    }

    private ClassUtils() {
        throw new UnsupportedOperationException();
    }

}

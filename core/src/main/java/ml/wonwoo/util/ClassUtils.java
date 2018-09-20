package ml.wonwoo.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ClassUtils {

    private static final Map<Class<?>, Class<?>> wrapperType;
    private static final Map<Class<?>, Class<?>> wrapperArrayType;

    static {
        final Map<Class<?>, Class<?>> wrapper = new ConcurrentHashMap<>();
        final Map<Class<?>, Class<?>> wrapperArray = new ConcurrentHashMap<>();

        wrapper.put(Boolean.class, boolean.class);
        wrapper.put(Byte.class, byte.class);
        wrapper.put(Character.class, char.class);
        wrapper.put(Short.class, short.class);
        wrapper.put(Integer.class, int.class);
        wrapper.put(Long.class, long.class);
        wrapper.put(Float.class, float.class);
        wrapper.put(Double.class, double.class);
        wrapper.put(String.class, String.class);

        wrapperArray.put(Boolean[].class, boolean[].class);
        wrapperArray.put(Byte[].class, byte[].class);
        wrapperArray.put(Character[].class, char[].class);
        wrapperArray.put(Short[].class, short[].class);
        wrapperArray.put(Integer[].class, int[].class);
        wrapperArray.put(Long[].class, long[].class);
        wrapperArray.put(Float[].class, float[].class);
        wrapperArray.put(Double[].class, double[].class);
        wrapperArray.put(String[].class, String[].class);

        wrapperType = Collections.unmodifiableMap(wrapper);
        wrapperArrayType = Collections.unmodifiableMap(wrapperArray);
    }

    public static boolean isWrapperType(Class<?> type) {
        return wrapperType.containsKey(type) || wrapperType.containsValue(type);
    }

    public static boolean isWrapperArrayType(Class<?> type) {
        return wrapperArrayType.containsKey(type) || wrapperArrayType.containsValue(type);
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static Type getGenericType(Method method) {
        return method.getGenericParameterTypes()[0];
    }

    private ClassUtils() {
        throw new UnsupportedOperationException();
    }

}

package ml.wonwoo.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;

public abstract class ClassUtils {

    private static final Map<Class<?>, Class<?>> wrapperType;
    private static final Map<Class<?>, Class<?>> defaultJavaType;

    private final static Set<Class<?>> typeCaches = new HashSet<>();
    private final static Set<Class<?>> allType = new HashSet<>();

    static {
        final Map<Class<?>, Class<?>> wrapper = new ConcurrentHashMap<>();
        final Map<Class<?>, Class<?>> javaType = new ConcurrentHashMap<>();

        wrapper.put(Boolean.class, boolean.class);
        wrapper.put(Byte.class, byte.class);
        wrapper.put(Character.class, char.class);
        wrapper.put(Short.class, short.class);
        wrapper.put(Integer.class, int.class);
        wrapper.put(Long.class, long.class);
        wrapper.put(Float.class, float.class);
        wrapper.put(Double.class, double.class);
        wrapper.put(String.class, String.class);

        javaType.put(Date.class, Date.class);
        javaType.put(Temporal.class, Temporal.class);
        javaType.put(Calendar.class, Calendar.class);
        javaType.put(Timestamp.class, Timestamp.class);
        javaType.put(java.sql.Date.class, java.sql.Date.class);
        javaType.put(Number.class, Number.class);
        javaType.put(CharSequence.class, CharSequence.class);

        if (isPresent("java.util.Optional")) {
            javaType.put(ChronoLocalDate.class, ChronoLocalDate.class);
            javaType.put(ChronoLocalDateTime.class, ChronoLocalDateTime.class);
            javaType.put(Temporal.class, Temporal.class);
        }

        if (isJoda()) {
            javaType.put(ReadableInstant.class, ReadableInstant.class);
            javaType.put(ReadableDateTime.class, ReadableDateTime.class);
        }

        if(isJsr354()) {
            javaType.put(CurrencyUnit.class, CurrencyUnit.class);
            javaType.put(MonetaryAmount.class, MonetaryAmount.class);
        }

        wrapperType = Collections.unmodifiableMap(wrapper);
        defaultJavaType = Collections.unmodifiableMap(javaType);

        allType.addAll(wrapperType.keySet());
        allType.addAll(wrapperType.values());
        allType.addAll(defaultJavaType.keySet());
    }

    public static boolean isObject(Class<?> type) {
        if (typeCaches.contains(type)) {
            return false;
        }
        for (Class<?> clazz : allType) {
            if (clazz.isAssignableFrom(type)) {
                typeCaches.add(type);
                return false;
            }
        }
        return true;
    }

    public static boolean isJsr354() {
        return isPresent("javax.money.MonetaryAmount");
    }

    public static boolean isJoda() {
        return isPresent("org.joda.time.DateTime");
    }

    public static boolean isWrapperType(Class<?> type) {
        return wrapperType.containsKey(type) || wrapperType.containsValue(type);
    }

    public static boolean isDefaultJavaType(Class<?> type) {
        return defaultJavaType.containsKey(type) || defaultJavaType.containsValue(type);
    }

    public static <T> T instantiateClass(Class<T> clazz, Object... args) {
        try {
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor.newInstance(args);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static Type getGenericType(Method method) {
        return method.getGenericParameterTypes()[0];
    }

    public static boolean isPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    private ClassUtils() {
        throw new UnsupportedOperationException();
    }

}

package ml.wonwoo.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

    private final static boolean IS_JAVAS_LANG = isPresent("io.vavr.collection.Seq");
    private final static boolean IS_JAVA_EIGHT =  isPresent("java.util.Optional");
    private final static boolean IS_MONEY =  isPresent("javax.money.MonetaryAmount");
    private final static boolean IS_JODA =  isPresent("org.joda.time.DateTime");


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

        if (isJava8()) {
            javaType.put(ChronoLocalDate.class, ChronoLocalDate.class);
            javaType.put(ChronoLocalDateTime.class, ChronoLocalDateTime.class);
            javaType.put(Temporal.class, Temporal.class);
        }

        if (isJoda()) {
            javaType.put(ReadableInstant.class, ReadableInstant.class);
            javaType.put(ReadableDateTime.class, ReadableDateTime.class);
        }

        if (isJsr354()) {
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

    public static boolean isJavasLang() {
        return IS_JAVAS_LANG;
    }

    public static boolean isJava8() {
        return IS_JAVA_EIGHT;
    }

    public static boolean isJsr354() {
        return IS_MONEY;
    }

    public static boolean isJoda() {
        return IS_JODA;
    }

    public static <T> T instantiateClass(Class<T> clazz) {
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

    public static boolean isPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }


    public static <T extends Annotation> T findMappingAnnotation(AnnotatedElement element, Class<T> annotation) {
        return element.getAnnotation(annotation);
    }

    public static Field[] findAllFields(Class<?> clazz) {
        if (clazz.isInterface()) {
            throw new IllegalArgumentException(clazz + " is an interface");
        }
        return clazz.getDeclaredFields();
    }

    private ClassUtils() {
        throw new UnsupportedOperationException();
    }

}

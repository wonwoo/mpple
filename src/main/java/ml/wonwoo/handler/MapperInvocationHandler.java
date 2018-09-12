package ml.wonwoo.handler;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import ml.wonwoo.mapped.Mapped;
import ml.wonwoo.util.Assert;

//TODO
public class MapperInvocationHandler implements InvocationHandler {

    private final Mapper<?> mapper;
    private final Mapped mapped;

    public MapperInvocationHandler(Mapper<?> mapper, Mapped mapped) {
        Assert.notNull(mapper, "mapper must not null");
        Assert.notNull(mapped, "mapped must not null");
        this.mapper = mapper;
        this.mapped = mapped;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("equals".equals(method.getName())) {
            try {
                Object otherHandler = args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                return equals(otherHandler);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else if ("hashCode".equals(method.getName())) {
            return hashCode();
        } else if ("toString".equals(method.getName())) {
            return toString();
        }

        if (args == null || args.length == 0 || args.length > 1) {
            throw new RuntimeException("arg exception");
        }
        if (isDefault(method)) {
            final Constructor<Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            final Class<?> declaringClass = method.getDeclaringClass();
            return constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE)
                .unreflectSpecial(method, declaringClass)
                .bindTo(proxy)
                .invokeWithArguments(args);
        }
        return mapped.map(args[0], method.getReturnType());
    }

    private boolean isDefault(Method method) {
//        method.isDefault()
        return ((method.getModifiers() & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC)
            && method.getDeclaringClass().isInterface();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MapperInvocationHandler) {
            MapperInvocationHandler other = (MapperInvocationHandler) obj;
            return mapper.equals(other.mapper);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mapper.hashCode();
    }

    @Override
    public String toString() {
        return mapper.toString();
    }


}

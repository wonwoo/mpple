package ml.wonwoo.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import ml.wonwoo.handler.Mapper;
import ml.wonwoo.handler.MapperInvocationHandler;
import ml.wonwoo.mapped.Mapped;

public interface Meca {

    <T> T newInstance(Mapper<T> target);

    static Meca create(Mapped mapped) {
        return new DefaultMeca(mapped);
    }

    class DefaultMeca implements Meca {

        private final Mapped mapped;

        DefaultMeca(Mapped mapped) {
            this.mapped = mapped;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T newInstance(Mapper<T> target) {
            InvocationHandler handler = new MapperInvocationHandler(target, this.mapped);
            return (T) Proxy.newProxyInstance(target.getType().getClassLoader(), new Class[]{target.getType()}, handler);
        }
    }
}

package ml.wonwoo.mapped;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public interface Mapped {

    <D> D map(Object source, Class<D> type);

    static Mapped create() {
        return new DefaultMapped();
    }

    //TODO exception
    class DefaultMapped implements Mapped {

        @Override
        public <D> D map(Object source, Class<D> type) {
            Field[] declaredFields = source.getClass().getDeclaredFields();
            try {
                Constructor<D> declaredConstructor = type.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                D instance = declaredConstructor.newInstance();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(source);
                    Field typeFidld = type.getDeclaredField(declaredField.getName());
                    if (typeFidld != null) {
                        if (!Modifier.isStatic(typeFidld.getModifiers())) {
                            typeFidld.setAccessible(true);
                            typeFidld.set(instance, obj);
                        }
                    }
                }
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}

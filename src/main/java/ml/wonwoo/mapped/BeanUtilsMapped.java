package ml.wonwoo.mapped;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsMapped implements Mapped {

    //TODO
    @Override
    public <D> D map(Object source, Class<D> type) {
        try {
            Constructor<D> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            D destination = constructor.newInstance();
            BeanUtils.copyProperties(destination, source);
            return destination;
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}

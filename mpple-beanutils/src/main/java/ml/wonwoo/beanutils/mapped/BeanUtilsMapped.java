package ml.wonwoo.beanutils.mapped;

import ml.wonwoo.mapped.Mapped;
import ml.wonwoo.util.ClassUtils;
import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsMapped implements Mapped {

    @Override
    public <D> D map(Object source, Class<D> type) {
        try {
            D destination = ClassUtils.instantiateClass(type);
            BeanUtils.copyProperties(destination, source);
            return destination;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

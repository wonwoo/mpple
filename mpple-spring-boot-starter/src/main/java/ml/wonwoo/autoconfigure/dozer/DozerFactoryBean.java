package ml.wonwoo.autoconfigure.dozer;

import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.FactoryBean;

public class DozerFactoryBean implements FactoryBean<DozerBeanMapper> {

    private List<DozerCustomizer> dozerCustomizers;

    @Override
    public DozerBeanMapper getObject() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        configure(dozerBeanMapper);
        return dozerBeanMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return DozerBeanMapper.class;
    }

    private void configure(DozerBeanMapper dozerBeanMapper) {
        if (dozerCustomizers != null) {
            dozerCustomizers.forEach(dozerCustomizer -> dozerCustomizer.customize(dozerBeanMapper));
        }
    }

    public void setDozerCustomizers(List<DozerCustomizer> dozerCustomizers) {
        this.dozerCustomizers = dozerCustomizers;
    }
}

package ml.wonwoo.spring;

import ml.wonwoo.Mpple;
import ml.wonwoo.mapped.Mapped;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.util.StringUtils;

public class MppleFactoryBean implements FactoryBean<Object>, BeanFactoryAware {

    private BeanFactory beanFactory;
    private Class<?> type;
    private Mapped mapped;
    private String beanName;

    @Override
    public Object getObject() {
        if (StringUtils.hasText(this.beanName)) {
            this.mapped = getBean(beanName, Mapped.class);
        }
        return Mpple.builder()
            .mapped(this.mapped)
            .target(type);
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public void setMapped(Mapped mapped) {
        this.mapped = mapped;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    private <T> T getBean(String beanName, Class<T> expectedType) {
        return BeanFactoryAnnotationUtils.qualifiedBeanOfType(this.beanFactory, expectedType, beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

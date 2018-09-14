package ml.wonwoo.autoconfigure.registrar;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import ml.wonwoo.spring.MppleFactoryBean;
import ml.wonwoo.spring.MppledsRegistrar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.core.type.AnnotationMetadata;

public class SpringBootMppledsRegistrar extends MppledsRegistrar implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    protected Set<String> getBasePackage(AnnotationMetadata annotationMetadata, Map<String, Object> mppledAnnotationAttributes) {
        return new HashSet<>(AutoConfigurationPackages.get(this.beanFactory));
    }

    @Override
    protected void registerBeanDefinitionMppled(AnnotationMetadata metadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        String className = metadata.getClassName();
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(MppleFactoryBean.class);
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
        definition.addPropertyValue("type", className);
        definition.addPropertyValue("beanName", "modelMapperMapped");
        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, beanDefinitionRegistry);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

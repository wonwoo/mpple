package ml.wonwoo.spring;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import ml.wonwoo.mapped.Mapped;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class MppledsRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, ResourceLoaderAware, BeanFactoryAware {

    private Environment environment;
    private ResourceLoader resourceLoader;
    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(EnableMppled.class.getName());
        Class<?>[] basePackageClasses = (Class<?>[]) annotationAttributes.get("basePackageClasses");
        Set<String> packageNames = new HashSet<>();
        if (basePackageClasses == null || basePackageClasses.length == 0) {
            packageNames.add(ClassUtils.getPackageName(annotationMetadata.getClassName()));
        } else {
            for (Class<?> basePackageClass : basePackageClasses) {
                packageNames.add(basePackageClass.getPackage().getName());
            }
        }

        ClassPathScanningCandidateComponentProvider scanner = scan();
        scanner.setResourceLoader(this.resourceLoader);
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(Mppled.class);
        scanner.addIncludeFilter(annotationTypeFilter);

        for (String packageName : packageNames) {
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(packageName);
            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata metadata = annotatedBeanDefinition.getMetadata();
                    Assert.isTrue(metadata.isInterface(), "@Mppled annotation only interface");
                    registerBeanDefinitionMppled(metadata, beanDefinitionRegistry);
                }
            }
        }
    }

    private void registerBeanDefinitionMppled(AnnotationMetadata metadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(Mppled.class.getCanonicalName());
        String className = metadata.getClassName();
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(MppleFactoryBean.class);
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
        definition.addPropertyValue("type", className);
        if (StringUtils.hasText((String) attributes.get("mapped"))) {
            Mapped mapped = getBean((String) attributes.get("mapped"), Mapped.class);
            definition.addPropertyValue("mapped", mapped);
        } else {
            definition.addPropertyValue("mapped", BeanUtils.instantiateClass((Class<?>) attributes.get("mappedClasses")));
        }
        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, beanDefinitionRegistry);
    }

    protected <T> T getBean(String beanName, Class<T> expectedType) {
        return BeanFactoryAnnotationUtils.qualifiedBeanOfType(this.beanFactory, expectedType, beanName);
    }

    protected ClassPathScanningCandidateComponentProvider scan() {
        return new MppleClassPathScanningCandidateComponentProvider(this.environment);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

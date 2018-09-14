package ml.wonwoo.spring;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
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

public class MppledsRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, ResourceLoaderAware {

    private Environment environment;
    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(EnableMppled.class.getName());
        Set<String> packageNames = getBasePackage(annotationMetadata, annotationAttributes);
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

    protected Set<String> getBasePackage(AnnotationMetadata annotationMetadata, Map<String, Object> mppledAnnotationAttributes) {
        Class<?>[] basePackageClasses = (Class<?>[]) mppledAnnotationAttributes.get("basePackageClasses");
        Set<String> packageNames = new HashSet<>();
        if (basePackageClasses == null || basePackageClasses.length == 0) {
            packageNames.add(ClassUtils.getPackageName(annotationMetadata.getClassName()));
        } else {
            for (Class<?> basePackageClass : basePackageClasses) {
                packageNames.add(basePackageClass.getPackage().getName());
            }
        }
        return packageNames;
    }

    protected void registerBeanDefinitionMppled(AnnotationMetadata metadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(Mppled.class.getCanonicalName());
        String className = metadata.getClassName();
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(MppleFactoryBean.class);
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
        definition.addPropertyValue("type", className);
        if (StringUtils.hasText((String) attributes.get("mapped"))) {
            definition.addPropertyValue("beanName", attributes.get("mapped"));
        } else {
            definition.addPropertyValue("mapped", BeanUtils.instantiateClass((Class<?>) attributes.get("mappedClasses")));
        }
        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, beanDefinitionRegistry);
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
}

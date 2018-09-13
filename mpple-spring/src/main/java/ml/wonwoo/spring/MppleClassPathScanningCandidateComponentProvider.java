package ml.wonwoo.spring;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;

public final class MppleClassPathScanningCandidateComponentProvider extends ClassPathScanningCandidateComponentProvider {

    public MppleClassPathScanningCandidateComponentProvider(Environment environment) {
        this(false, environment);
    }

    public MppleClassPathScanningCandidateComponentProvider(boolean useDefaultFilters, Environment environment) {
        super(useDefaultFilters, environment);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        if (beanDefinition.getMetadata().isIndependent()) {
            return !beanDefinition.getMetadata().isAnnotation();
        }
        return false;
    }
}

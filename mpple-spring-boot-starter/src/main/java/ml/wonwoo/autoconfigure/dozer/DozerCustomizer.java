package ml.wonwoo.autoconfigure.dozer;

import org.dozer.DozerBeanMapper;

@FunctionalInterface
public interface DozerCustomizer {

    void customize(DozerBeanMapper dozerBeanMapper);
}

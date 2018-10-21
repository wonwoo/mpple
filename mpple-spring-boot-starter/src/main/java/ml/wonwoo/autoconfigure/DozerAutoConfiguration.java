package ml.wonwoo.autoconfigure;

import java.util.List;
import ml.wonwoo.autoconfigure.dozer.DozerCustomizer;
import ml.wonwoo.autoconfigure.dozer.DozerFactoryBean;
import ml.wonwoo.dozer.mapped.DozerMapped;
import ml.wonwoo.mapped.Mapped;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DozerMapped.class)
@Conditional(MapperCondition.class)
public class DozerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FactoryBean<DozerBeanMapper> mapperFactoryBean(ObjectProvider<List<DozerCustomizer>> provider) {
        DozerFactoryBean dozerFactoryBean = new DozerFactoryBean();
        dozerFactoryBean.setDozerCustomizers(provider.getIfAvailable());
        return dozerFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(Mapper.class)
    public Mapped mapped(Mapper mapper) {
        DozerMapped dozerMapped = new DozerMapped();
        dozerMapped.setMapper(mapper);
        return dozerMapped;
    }
}

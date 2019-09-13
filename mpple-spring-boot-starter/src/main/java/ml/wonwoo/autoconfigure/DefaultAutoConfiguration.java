package ml.wonwoo.autoconfigure;

import ml.wonwoo.mapped.DefaultMapped;
import ml.wonwoo.mapped.Mapped;
import ml.wonwoo.mapped.converter.MappedConverter;
import ml.wonwoo.mapped.mapping.MappingInstanceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Conditional(MapperCondition.class)
public class DefaultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Mapped.class)
    public Mapped mapped(List<MappedConverter> mappedConverters) {
        DefaultMapped mapped = new DefaultMapped();
        MappingInstanceImpl mappingInstance = new MappingInstanceImpl();
        mappedConverters.forEach(mappingInstance::setConverter);
        mapped.setMappingInstance(mappingInstance);
        return mapped;
    }
}

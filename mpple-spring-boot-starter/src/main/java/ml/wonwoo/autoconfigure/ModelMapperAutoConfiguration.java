package ml.wonwoo.autoconfigure;

import java.util.List;
import ml.wonwoo.autoconfigure.modelmapper.ConverterCustomizer;
import ml.wonwoo.autoconfigure.modelmapper.ModelMapperCustomizer;
import ml.wonwoo.autoconfigure.modelmapper.ModelMapperFactoryBean;
import ml.wonwoo.autoconfigure.modelmapper.PropertyMapCustomizer;
import ml.wonwoo.mapped.Mapped;
import ml.wonwoo.modelmapper.mapped.ModelMapperMapped;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass(ModelMapperMapped.class)
@Conditional(MapperCondition.class)
@Configuration
public class ModelMapperAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Mapped mapped(ModelMapper modelMapper) {
        ModelMapperMapped modelMapperMapped = new ModelMapperMapped();
        modelMapperMapped.setModelMapper(modelMapper);
        return modelMapperMapped;
    }

    @Bean
    @ConditionalOnMissingBean
    public FactoryBean<ModelMapper> mapperFactoryBean(ObjectProvider<List<ModelMapperCustomizer>> provider) {
        ModelMapperFactoryBean modelMapperFactoryBean = new ModelMapperFactoryBean();
        modelMapperFactoryBean.setMapperCustomizers(provider.getIfAvailable());
        return modelMapperFactoryBean;
    }

    @Bean
    public PropertyMapCustomizer propertyMapCustomizer(ObjectProvider<List<PropertyMap<?, ?>>> provider) {
        PropertyMapCustomizer propertyMapCustomizer = new PropertyMapCustomizer();
        propertyMapCustomizer.setPropertyMaps(provider.getIfAvailable());
        return propertyMapCustomizer;
    }

    @Bean
    public ConverterCustomizer converterCustomizer(ObjectProvider<List<Converter<?, ?>>> provider) {
        ConverterCustomizer converterCustomizer = new ConverterCustomizer();
        converterCustomizer.setConverters(provider.getIfAvailable());
        return converterCustomizer;
    }
}

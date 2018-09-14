package ml.wonwoo.autoconfigure.modelmapper;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.FactoryBean;

public class ModelMapperFactoryBean implements FactoryBean<ModelMapper> {

    private List<ModelMapperCustomizer> mapperCustomizers;

    @Override
    public ModelMapper getObject() {
        ModelMapper modelMapper = new ModelMapper();
        configure(modelMapper);
        return modelMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return ModelMapper.class;
    }

    private void configure(ModelMapper modelMapper) {
        if (mapperCustomizers == null) {
            return;
        }
        mapperCustomizers.forEach(modelCustomizer -> modelCustomizer.customize(modelMapper));
    }

    public void setMapperCustomizers(List<ModelMapperCustomizer> mapperCustomizers) {
        this.mapperCustomizers = mapperCustomizers;
    }
}

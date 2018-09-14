package ml.wonwoo.autoconfigure.modelmapper;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelMapperFactoryBeanTest {

    @Test
    public void modelMapperTest() {
        ModelMapperFactoryBean modelMapperFactoryBean = new ModelMapperFactoryBean();
        ModelMapper modelMapper = modelMapperFactoryBean.getObject();
        Class<?> objectType = modelMapperFactoryBean.getObjectType();
        assertThat(modelMapper).isNotNull();
        assertThat(objectType).isEqualTo(ModelMapper.class);
    }

    @Test
    public void modelMapperCustomizerTest() {
        ModelMapperFactoryBean modelMapperFactoryBean = new ModelMapperFactoryBean();
        modelMapperFactoryBean.setMapperCustomizers(Collections.singletonList(modelMapper -> assertThat(modelMapper).isNotNull()));
        ModelMapper modelMapper = modelMapperFactoryBean.getObject();
        Class<?> objectType = modelMapperFactoryBean.getObjectType();
        assertThat(modelMapper).isNotNull();
        assertThat(objectType).isEqualTo(ModelMapper.class);
    }

}
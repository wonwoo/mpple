package ml.wonwoo.autoconfigure.dozer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import org.dozer.DozerBeanMapper;
import org.junit.Test;

public class DozerFactoryBeanTests {

    @Test
    public void dozerMapperTest() {
        DozerFactoryBean dozerFactoryBean = new DozerFactoryBean();
        DozerBeanMapper dozerBeanMapper = dozerFactoryBean.getObject();
        Class<?> objectType = dozerFactoryBean.getObjectType();
        assertThat(dozerBeanMapper).isNotNull();
        assertThat(objectType).isEqualTo(DozerBeanMapper.class);
    }

    @Test
    public void dozerMapperCustomizerTest() {
        DozerFactoryBean dozerFactoryBean = new DozerFactoryBean();
        dozerFactoryBean.setDozerCustomizers(Collections.singletonList(dozerBeanMapper -> assertThat(dozerBeanMapper).isNotNull()));
        DozerBeanMapper dozerBeanMapper = dozerFactoryBean.getObject();
        Class<?> objectType = dozerFactoryBean.getObjectType();
        assertThat(dozerBeanMapper).isNotNull();
        assertThat(objectType).isEqualTo(DozerBeanMapper.class);
    }
}
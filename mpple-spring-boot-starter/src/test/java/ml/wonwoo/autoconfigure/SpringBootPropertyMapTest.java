package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.autoconfigure.model.Foo;
import ml.wonwoo.autoconfigure.model.FooDto;
import ml.wonwoo.autoconfigure.model.FooMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootPropertyMapTest {

    @Autowired
    private FooMapper fooMapper;

    @Test
    public void propertyMappingTest() {
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooMapper.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("lee");
        assertThat(fooDto.getLastName()).isEqualTo("wonwoo");
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        PropertyMap<Foo, FooDto> fooDtoFooPropertyMap() {
            return new PropertyMap<Foo, FooDto>() {
                @Override
                protected void configure() {
                    map(source.getLastName(), destination.getFirstName());
                    map(source.getFirstName(), destination.getLastName());
                }
            };
        }
    }
}


package ml.wonwoo.autoconfigure;

import ml.wonwoo.autoconfigure.model.*;
import ml.wonwoo.mapped.converter.MappedConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootDefaultTests {

    @Autowired
    private FooMapper fooMapper;

    @Autowired
    private CustomFooMapper customFooMapper;

    @Test
    public void defaultTest() {
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooMapper.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }

    @Test
    public void converterCustomizedTest() {

        CustomFoo customFoo = new CustomFoo();
        customFoo.setName("wonwoo");
        CustomFooDto customFooDto = customFooMapper.customFooDto(customFoo);
        assertThat(customFooDto.getName().get()).isEqualTo("wonwoo");
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        CustomFooConverter customFooConverter() {
            return new CustomFooConverter();
        }

    }

    static class CustomFooConverter implements MappedConverter {

        @Override
        public boolean supports(Class<?> target) {
            return target.isAssignableFrom(Optional.class);
        }

        @Override
        public Object convert(Class<?> rootClass, Object value, Class<?> target, Object context) {

            return Optional.of(value);
        }
    }
}

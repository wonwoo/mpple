package ml.wonwoo.spring.inner;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.modelmapper.mapped.ModelMapperMapped;
import ml.wonwoo.spring.EnableMppled;
import ml.wonwoo.spring.FooBar;
import ml.wonwoo.spring.MppleSpringConfig;
import ml.wonwoo.spring.model.Foo;
import ml.wonwoo.spring.model.FooDto;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class SpringBasePackageTests {

    @Test
    public void basePackageTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BasePackage.class);
        FooBar fooBar = context.getBean(FooBar.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooBar.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }

    @EnableMppled(basePackageClasses = MppleSpringConfig.class)
    private static class BasePackage {

        @Bean
        ModelMapperMapped modelMapperMapped() {
            return new ModelMapperMapped();
        }
    }
}

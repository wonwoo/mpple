package ml.wonwoo.spring;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.MppleSpringConfig;
import ml.wonwoo.mapped.ModelMapperMapped;
import ml.wonwoo.model.Foo;
import ml.wonwoo.model.FooDto;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTests {

    @Test
    public void springTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MppleSpringConfig.class);
        FooBar fooBar = context.getBean(FooBar.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooBar.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }


    @Test
    public void mappedTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MppleSpringConfig.class);
        Mapp fooBar = context.getBean(Mapp.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooBar.bar(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }


    @Mppled(mapped = ModelMapperMapped.class)
    interface Mapp {
        FooDto bar(Foo foo);
    }

}

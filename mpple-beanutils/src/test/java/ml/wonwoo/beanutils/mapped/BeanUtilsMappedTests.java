package ml.wonwoo.beanutils.mapped;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.Mpple;
import ml.wonwoo.beanutils.mapped.model.Foo;
import ml.wonwoo.beanutils.mapped.model.FooDto;
import ml.wonwoo.beanutils.mapped.model.FooMapper;
import org.junit.Test;

public class BeanUtilsMappedTests {

    @Test
    public void beanUtilsTest() {
        FooMapper fooMapper = Mpple.builder()
            .mapped(new BeanUtilsMapped())
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooMapper.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }
}
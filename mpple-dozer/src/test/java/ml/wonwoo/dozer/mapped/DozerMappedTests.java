package ml.wonwoo.dozer.mapped;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.Mpple;
import ml.wonwoo.dozer.mapped.model.Foo;
import ml.wonwoo.dozer.mapped.model.FooDto;
import ml.wonwoo.dozer.mapped.model.FooMapper;
import org.dozer.DozerBeanMapper;
import org.junit.Test;

public class DozerMappedTests {

    @Test
    public void dozerTest() {
        DozerMapped mapped = new DozerMapped();
        mapped.setMapper(new DozerBeanMapper());
        FooMapper fooMapper = Mpple.builder()
            .mapped(mapped)
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooMapper.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }
}
package ml.wonwoo.modelmapper.mapped;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.Mpple;
import ml.wonwoo.modelmapper.mapped.model.Foo;
import ml.wonwoo.modelmapper.mapped.model.FooDto;
import ml.wonwoo.modelmapper.mapped.model.FooMapper;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class ModelMapperMappedTests {

    @Test
    public void modelMapperTest() {
        ModelMapperMapped mapped = new ModelMapperMapped();
        mapped.setModelMapper(new ModelMapper());
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
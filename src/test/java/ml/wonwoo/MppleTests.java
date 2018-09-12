package ml.wonwoo;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.mapped.BeanUtilsMapped;
import ml.wonwoo.mapped.DozerMapped;
import ml.wonwoo.mapped.ModelMapperMapped;
import ml.wonwoo.model.Foo;
import ml.wonwoo.model.FooDto;
import ml.wonwoo.model.FooMapper;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class MppleTests {

    @Test
    public void defaultTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        assertThat(fooMapper.bar(foo)).isEqualTo("wonwoo");
    }

    @Test
    public void mapperTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooMapper.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }

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
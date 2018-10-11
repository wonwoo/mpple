package ml.wonwoo;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.model.Foo;
import ml.wonwoo.model.FooDto;
import ml.wonwoo.model.FooMapper;
import org.junit.Test;

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

    @Test(expected = IllegalArgumentException.class)
    public void voidReturnTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        fooMapper.fooBar(foo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void objectReturnTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        Object poo = fooMapper.poo(foo);
        assertThat(poo).isNull();
    }
}
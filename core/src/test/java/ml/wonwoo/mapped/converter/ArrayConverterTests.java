package ml.wonwoo.mapped.converter;


import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.mapped.mapping.MappingInstanceImpl;
import ml.wonwoo.model.Foo;
import org.junit.Test;

public class ArrayConverterTests {

    private final ArrayConverter converter = new ArrayConverter(new MappingInstanceImpl());


    @Test
    public void supports() {
        assertThat(converter.supports(Boolean[].class)).isTrue();
        assertThat(converter.supports(Byte[].class)).isTrue();
        assertThat(converter.supports(Character[].class)).isTrue();
        assertThat(converter.supports(Short[].class)).isTrue();
        assertThat(converter.supports(Integer[].class)).isTrue();
        assertThat(converter.supports(Long[].class)).isTrue();
        assertThat(converter.supports(Float[].class)).isTrue();
        assertThat(converter.supports(Double[].class)).isTrue();
        assertThat(converter.supports(String[].class)).isTrue();
        assertThat(converter.supports(Foo[].class)).isTrue();
    }

    @Test
    public void convertType() {
        Foo foo = new Foo();
        foo.setFirstName("first");
        foo.setLastName("last");
        Foo[] foos = new Foo[] {foo};
        Object obj = converter.convert(FooArray.class, foos, Foo[].class, "setFoos");
        Foo[] array = (Foo[]) obj;
        assertThat(array[0].getFirstName()).isEqualTo("first");
        assertThat(array[0].getLastName()).isEqualTo("last");
    }

    public static class FooArray {

        private Foo[] foos;

        public Foo[] getFoos() {
            return foos;
        }

        public void setFoos(Foo[] foos) {
            this.foos = foos;
        }
    }
}
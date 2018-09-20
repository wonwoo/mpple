package ml.wonwoo.mapped.converter;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.model.Foo;
import org.junit.Test;

public class WrapperTypeConverterTests {


    private final WrapperTypeConverter converter = new WrapperTypeConverter();

    @Test
    public void supports() {
        assertThat(converter.supports(Boolean.class)).isTrue();
        assertThat(converter.supports(Byte.class)).isTrue();
        assertThat(converter.supports(Character.class)).isTrue();
        assertThat(converter.supports(Short.class)).isTrue();
        assertThat(converter.supports(Integer.class)).isTrue();
        assertThat(converter.supports(Long.class)).isTrue();
        assertThat(converter.supports(Float.class)).isTrue();
        assertThat(converter.supports(Double.class)).isTrue();
        assertThat(converter.supports(String.class)).isTrue();
        assertThat(converter.supports(Foo.class)).isFalse();
    }

    @Test
    public void convert() {
        assertThat(converter.convert(null, "test", null, null))
            .isEqualTo("test");
    }
}
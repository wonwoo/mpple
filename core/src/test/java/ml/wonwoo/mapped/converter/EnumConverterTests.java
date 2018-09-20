package ml.wonwoo.mapped.converter;


import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.model.Foo;
import org.junit.Test;

public class EnumConverterTests {

    private final EnumConverter converter = new EnumConverter();

    @Test
    public void supports() {
        assertThat(converter.supports(EnumTest.class)).isTrue();
        assertThat(converter.supports(Foo.class)).isFalse();
    }

    @Test
    public void convert() {
        EnumFoo enumFoo = new EnumFoo();
        enumFoo.setEnumTest(EnumTest.Y);
        Object obj = converter.convert(EnumFoo.class, EnumTest.Y, EnumTest.class, "setEnumTest");
        EnumTest enumTest = (EnumTest) obj;
        assertThat(enumTest).isEqualTo(EnumTest.Y);
    }

    public enum EnumTest {
        Y,
        N
    }

    public static class EnumFoo {

        private EnumTest enumTest;

        public void setEnumTest(EnumTest enumTest) {
            this.enumTest = enumTest;
        }

        public EnumTest getEnumTest() {
            return enumTest;
        }
    }
}
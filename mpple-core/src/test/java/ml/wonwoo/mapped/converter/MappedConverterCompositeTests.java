package ml.wonwoo.mapped.converter;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MappedConverterCompositeTests {


    @Test
    public void supportsTest() {

        MappedConverterComposite mappedConverterComposite = new MappedConverterComposite();

        mappedConverterComposite.addConverter(new WrapperTypeConverter());

        assertThat(mappedConverterComposite.supports(String.class)).isTrue();

    }

    @Test
    public void convertTest() {

        MappedConverterComposite mappedConverterComposite = new MappedConverterComposite();

        mappedConverterComposite.addConverter(new WrapperTypeConverter());

        Object convert = mappedConverterComposite.convert(String.class, "wonwoo", String.class, "foo");

        assertThat(convert).isEqualTo("wonwoo");
    }

    @Test
    public void customConvertTest() {

        MappedConverterComposite mappedConverterComposite = new MappedConverterComposite();

        mappedConverterComposite.addConverter(new WrapperTypeConverter());

        mappedConverterComposite.setConverter(new CustomConverter());

        Object convert = mappedConverterComposite.convert(String.class, "wonwoo", String.class, "foo");

        assertThat(convert).isEqualTo("wonwoo dto");

    }

    static class CustomConverter implements MappedConverter {

        @Override
        public boolean supports(Class<?> target) {
            return true;
        }

        @Override
        public Object convert(Class<?> rootClass, Object value, Class<?> target, Object context) {
            return "wonwoo dto";
        }
    }

}

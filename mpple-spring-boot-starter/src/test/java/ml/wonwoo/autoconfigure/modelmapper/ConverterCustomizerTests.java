package ml.wonwoo.autoconfigure.modelmapper;

import org.junit.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterCustomizerTests {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void converter() {
        ConverterCustomizer converterCustomizer = new ConverterCustomizer();
        converterCustomizer.setConverters(Collections.singletonList(new Converter<String, Type>() {
            @Override
            public Type convert(MappingContext<String, Type> context) {
                return Type.valueOf(context.getSource());

            }
        }));
        converterCustomizer.customize(this.modelMapper);
        Foo foo = new Foo();
        foo.setType("FOO");
        FooDto fooDto = modelMapper.map(foo, FooDto.class);
        assertThat(fooDto.getType()).isEqualTo(Type.FOO);
    }


    static class Foo {
        private String type;

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
    static class FooDto {
        private Type type;

        public void setType(Type type) {
            this.type = type;
        }

        public Type getType() {
            return type;
        }
    }

    enum Type {
        FOO,
        BAR
    }

}
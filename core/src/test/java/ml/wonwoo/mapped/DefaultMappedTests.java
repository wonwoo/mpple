package ml.wonwoo.mapped;


import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.mapped.converter.MappedConverter;
import ml.wonwoo.model.Foo;
import ml.wonwoo.model.FooDto;
import org.junit.Test;

public class DefaultMappedTests {

    @Test
    public void map() {
        DefaultMapped defaultMapped = new DefaultMapped();
        MappingInstance mappingInstance = new MappingInstance();
        mappingInstance.addConverter(new MappedConverter() {
            @Override
            public boolean supports(Class<?> target) {
                return true;
            }

            @Override
            public Object convert(Class<?> clazz, Object value, Class<?> target, Object context) {
                return value;
            }
        });
        defaultMapped.setMappingInstance(mappingInstance);
        Foo foo = new Foo();
        foo.setLastName("last");
        foo.setFirstName("first");
        FooDto fooDto = defaultMapped.map(foo, FooDto.class);
        assertThat(fooDto.getLastName()).isEqualTo("last");
        assertThat(fooDto.getFirstName()).isEqualTo("first");
    }
}
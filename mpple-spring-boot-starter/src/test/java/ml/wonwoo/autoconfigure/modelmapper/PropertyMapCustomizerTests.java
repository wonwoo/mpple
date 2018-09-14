package ml.wonwoo.autoconfigure.modelmapper;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyMapCustomizerTests {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void propertyMap() {

        PropertyMapCustomizer propertyMapCustomizer = new PropertyMapCustomizer();
        propertyMapCustomizer.setPropertyMaps(Collections.singletonList(new PropertyMap<Foo, FooDto>() {
            @Override
            protected void configure() {
                map(source.getName(), destination.getUsername());
            }
        }));

        propertyMapCustomizer.customize(this.modelMapper);

        Foo foo = new Foo();
        foo.setName("daily");
        FooDto fooDto = this.modelMapper.map(foo, FooDto.class);
        assertThat(fooDto.getUsername()).isEqualTo("daily");
    }

    static class Foo {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class FooDto {

        private String username;

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}
package ml.wonwoo.mapped.converter;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import ml.wonwoo.mapped.mapping.MappingInstanceImpl;
import ml.wonwoo.model.Foo;
import org.junit.Test;

public class MapConverterTests {

    private final MapConverter mapConverter
        = new MapConverter(new MappingInstanceImpl());

    @Test
    public void supports() {
        assertThat(mapConverter.supports(Map.class)).isTrue();
        assertThat(mapConverter.supports(HashMap.class)).isTrue();
        assertThat(mapConverter.supports(LinkedHashMap.class)).isTrue();
        assertThat(mapConverter.supports(Foo.class)).isFalse();
    }

    @Test
    public void convert() {
        FooMap fooMap = new FooMap();
        Map<String, Foo> mapFoo = new HashMap<>();
        Foo foo = new Foo();
        foo.setFirstName("first");
        foo.setLastName("last");
        mapFoo.put("key", foo);
        fooMap.setMapFoo(mapFoo);

        Object result = mapConverter.convert(FooMap.class, mapFoo, Map.class, "setMapFoo");
        Map<String,Foo> map = (Map<String, Foo>) result;
        Foo key = map.get("key");
        assertThat(key.getFirstName()).isEqualTo("first");
        assertThat(key.getLastName()).isEqualTo("last");

    }

    public static class FooMap {

        private Map<String, Foo> mapFoo;

        public void setMapFoo(Map<String, Foo> mapFoo) {
            this.mapFoo = mapFoo;
        }

        public Map<String, Foo> getMapFoo() {
            return mapFoo;
        }
    }
}
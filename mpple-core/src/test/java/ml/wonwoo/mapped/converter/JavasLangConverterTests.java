package ml.wonwoo.mapped.converter;


import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import ml.wonwoo.mapped.mapping.MappingInstanceImpl;
import ml.wonwoo.model.Foo;
import org.junit.Test;

public class JavasLangConverterTests {


    private final JavasLangConverter javasLangConverter
        = new JavasLangConverter(new MappingInstanceImpl());

    @Test
    public void supports() {
        assertThat(javasLangConverter.supports(List.class)).isTrue();
        assertThat(javasLangConverter.supports(Seq.class)).isTrue();
        assertThat(javasLangConverter.supports(Foo.class)).isFalse();
    }

    @Test
    public void convertWrapperType() {
        List<String> strs = List.of("test", "foo");
        FooList fooList = new FooList();
        fooList.setStrs(strs);
        Object result = javasLangConverter.convert(FooList.class, strs, List.class, "setStrs");
        List list = (List) result;
        assertThat(list.get(0)).isEqualTo("test");
        assertThat(list.get(1)).isEqualTo("foo");
    }

    @Test
    public void convertObjectType() {
        Foo foo = new Foo();
        foo.setLastName("last");
        foo.setFirstName("first");
        List<Foo> foos = List.of(foo);
        FooList fooList = new FooList();
        fooList.setFoos(foos);
        Object result = javasLangConverter.convert(FooList.class, foos, List.class, "setFoos");
        List<Foo> list = (List) result;
        assertThat(list.get(0).getLastName()).isEqualTo("last");
        assertThat(list.get(0).getFirstName()).isEqualTo("first");
    }


    public static class FooList {

        private List<String> strs;
        private List<Foo> foos;

        public List<String> getStrs() {
            return strs;
        }

        public List<Foo> getFoos() {
            return foos;
        }

        public void setStrs(List<String> strs) {
            this.strs = strs;
        }

        public void setFoos(List<Foo> foos) {
            this.foos = foos;
        }
    }
}
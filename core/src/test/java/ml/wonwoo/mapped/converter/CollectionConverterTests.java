package ml.wonwoo.mapped.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import ml.wonwoo.mapped.mapping.MappingInstanceImpl;
import ml.wonwoo.model.Foo;
import org.junit.Test;

public class CollectionConverterTests {

    private final CollectionConverter collectionConverter
        = new CollectionConverter(new MappingInstanceImpl());

    @Test
    public void supports() {
        assertThat(collectionConverter.supports(Collection.class)).isTrue();
        assertThat(collectionConverter.supports(List.class)).isTrue();
        assertThat(collectionConverter.supports(Set.class)).isTrue();
        assertThat(collectionConverter.supports(TreeSet.class)).isTrue();
        assertThat(collectionConverter.supports(HashSet.class)).isTrue();
        assertThat(collectionConverter.supports(SortedSet.class)).isTrue();
        assertThat(collectionConverter.supports(Foo.class)).isFalse();
    }

    @Test
    public void convertWrapperType() {
        List<String> strs = new ArrayList<>();
        strs.add("test");
        strs.add("foo");
        FooList fooList = new FooList();
        fooList.setStrs(strs);
        Object result = collectionConverter.convert(FooList.class, strs, List.class, "setStrs");
        List list = (List) result;
        assertThat(list.get(0)).isEqualTo("test");
        assertThat(list.get(1)).isEqualTo("foo");

    }

    @Test
    public void convertObjectType() {
        Foo foo = new Foo();
        foo.setLastName("last");
        foo.setFirstName("first");
        List<Foo> foos = new ArrayList<>();
        foos.add(foo);
        FooList fooList = new FooList();
        fooList.setFoos(foos);
        Object result = collectionConverter.convert(FooList.class, foos, List.class, "setFoos");
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
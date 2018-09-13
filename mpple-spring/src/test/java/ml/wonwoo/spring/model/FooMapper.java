package ml.wonwoo.spring.model;

public interface FooMapper {

    FooDto foo(Foo foo);

    default String bar(Foo n) {
        return n.getFirstName();
    }
}

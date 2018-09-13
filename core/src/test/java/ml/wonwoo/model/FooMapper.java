package ml.wonwoo.model;

public interface FooMapper {

    FooDto foo(Foo foo);

    void fooBar(Foo foo);

    Object poo(Object foo);

    default String bar(Foo n) {
        return n.getFirstName();
    }
}

package ml.wonwoo.beanutils.mapped.model;

public interface FooMapper {

    FooDto foo(Foo foo);

    default String bar(Foo n) {
        return n.getFirstName();
    }
}

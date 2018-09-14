package ml.wonwoo.autoconfigure.model;

import ml.wonwoo.spring.Mppled;

@Mppled
public interface FooMapper {

    FooDto foo(Foo foo);

    default String bar(Foo n) {
        return n.getFirstName();
    }
}

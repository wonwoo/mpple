package ml.wonwoo;

import ml.wonwoo.spring.Mppled;

@Mppled
public interface FooMapper {

    FooDto foo(Foo foo);

    default String bar(Foo n) {
        return n.getFirstName();
    }
}

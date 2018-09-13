package ml.wonwoo.spring;

import ml.wonwoo.model.Foo;
import ml.wonwoo.model.FooDto;

@Mppled
public interface FooBar {
    FooDto foo(Foo foo);
}

package ml.wonwoo.spring;


import ml.wonwoo.spring.model.Foo;
import ml.wonwoo.spring.model.FooDto;

@Mppled
public interface FooBar {
    FooDto foo(Foo foo);
}

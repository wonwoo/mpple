package ml.wonwoo.autoconfigure.model;

import ml.wonwoo.spring.Mppled;

@Mppled
public interface CustomFooMapper {

    CustomFooDto customFooDto(CustomFoo customFoo);
}

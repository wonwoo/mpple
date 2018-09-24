# mpple

[![Build Status](https://travis-ci.org/wonwoo/mpple.svg?branch=master)](https://travis-ci.org/wonwoo/mpple)
[![Build Status](https://semaphoreci.com/api/v1/wonwoo/mpple/branches/master/badge.svg)](https://semaphoreci.com/wonwoo/mpple)
[![Coverage Status](https://coveralls.io/repos/github/wonwoo/mpple/badge.svg?branch=master)](https://coveralls.io/github/wonwoo/mpple?branch=master)

## default sample

```java

public class Foo {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


public class FooDto {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


public interface FooMapper {

    FooDto foo(Foo foo);
}

public static void main(String[] args) {
    FooMapper fooMapper = Mpple.builder()
        .target(FooMapper.class);
    Foo foo = new Foo();
    foo.setFirstName("wonwoo");
    foo.setLastName("lee");
    assertThat(fooMapper.foo(foo).getFirstName()).isEqualTo("wonwoo");
}

```

### for spring 

```java
@EnableMppled
public class MppleSpringConfig {

}

@Mppled
public interface FooMapper {
    FooDto foo(Foo foo);
}

@Autowired
private FooMapper fooMapper;

@Test
public void mappingTest() {
    Foo foo = new Foo();
    foo.setFirstName("wonwoo");
    foo.setLastName("lee");
    FooDto fooDto = fooMapper.foo(foo);
    assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
    assertThat(fooDto.getLastName()).isEqualTo("lee");
}

```

### for spring boot

> spring boot is default modelmapper 

```java
@Mppled
public interface FooMapper {

    FooDto foo(Foo foo);   
}

@Autowired
private FooMapper fooMapper;

@Test
public void mappingTest() {
    Foo foo = new Foo();
    foo.setFirstName("wonwoo");
    foo.setLastName("lee");
    FooDto fooDto = fooMapper.foo(foo);
    assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
    assertThat(fooDto.getLastName()).isEqualTo("lee");
}

```
### config

TODO 
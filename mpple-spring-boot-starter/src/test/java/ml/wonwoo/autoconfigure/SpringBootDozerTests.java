package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.autoconfigure.model.Foo;
import ml.wonwoo.autoconfigure.model.FooDto;
import ml.wonwoo.autoconfigure.model.FooMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(properties = "mpple.mapper.type=dozer")
@RunWith(SpringRunner.class)
public class SpringBootDozerTests {

    @Autowired
    private FooMapper fooMapper;

    @Test
    public void dozerTest() {
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooMapper.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }
}


package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.autoconfigure.model.Foo;
import ml.wonwoo.autoconfigure.model.FooDto;
import ml.wonwoo.autoconfigure.model.FooMapper;
import ml.wonwoo.spring.EnableMppled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableMppled
public class CustomEnableMppledTests {

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
}

package ml.wonwoo.spring.inner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import ml.wonwoo.spring.EnableMppled;
import ml.wonwoo.spring.FooBar;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringNoConfigTest {

    @Test
    public void notSpringTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(NoSpringConfig.class);
        Map<String, FooBar> types = context.getBeansOfType(FooBar.class);
        assertThat(types).hasSize(0);
    }

    @EnableMppled
    private static class NoSpringConfig {

    }
}

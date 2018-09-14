package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.autoconfigure.MppledsRegistrarAutoConfiguration.EnableMppledConfiguration;
import ml.wonwoo.spring.EnableMppled;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class MppledsRegistrarAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(MppledsRegistrarAutoConfiguration.class));

    @Test
    public void registersAutomatically() {
        contextRunner
            .withUserConfiguration(SpringBootTestConfig.class)
            .run(context -> assertThat(context).hasSingleBean(EnableMppledConfiguration.class));
    }

    @Test
    public void enableMppled() {
        contextRunner
            .withUserConfiguration(SpringBootTestConfig.class, TestConfig.class)
            .run(context -> assertThat(context).doesNotHaveBean(EnableMppledConfiguration.class));
    }

    @EnableMppled
    static class TestConfig {}
}
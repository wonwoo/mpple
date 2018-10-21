package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.mapped.DefaultMapped;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class DefaultAutoConfigurationTests {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(DefaultAutoConfiguration.class));

    @Test
    public void registersAutomatically() {
        runner
            .run(context -> assertThat(context).hasSingleBean(DefaultMapped.class));
    }

    @Test
    public void registersAutomaticallyNone() {
        runner
            .withPropertyValues("mpple.mapper.type=none")
            .run(context -> assertThat(context).hasSingleBean(DefaultMapped.class));
    }

    @Test
    public void registersAutomaticallyMiss() {
        runner
            .withPropertyValues("mpple.mapper.type=modelmapper")
            .run(context -> assertThat(context).doesNotHaveBean(DefaultMapped.class));
    }
}
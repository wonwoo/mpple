package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.dozer.mapped.DozerMapped;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class DozerAutoConfigurationTests {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(DozerAutoConfiguration.class));

    @Test
    public void registersAutomatically() {
        runner
            .withPropertyValues("mpple.mapper.type=dozer")
            .run(context -> {
                assertThat(context).hasSingleBean(DozerBeanMapper.class);
                assertThat(context).hasSingleBean(DozerMapped.class);
            });
    }

    @Test
    public void registersAutomaticallyMiss() {
        runner
            .withPropertyValues("mpple.mapper.type=modelmapper")
            .run(context -> assertThat(context).doesNotHaveBean(DozerBeanMapper.class));
    }
}
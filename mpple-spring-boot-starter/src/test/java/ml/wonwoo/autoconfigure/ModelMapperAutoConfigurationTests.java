package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.modelmapper.mapped.ModelMapperMapped;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class ModelMapperAutoConfigurationTests {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(ModelMapperAutoConfiguration.class));

    @Test
    public void registersAutomatically() {
        runner
            .withPropertyValues("mpple.mapper.type=modelmapper")
            .run(context -> {
                assertThat(context).hasSingleBean(ModelMapper.class);
                assertThat(context).hasSingleBean(ModelMapperMapped.class);
            });
    }

    @Test
    public void registersAutomaticallyMiss() {
        runner
            .withPropertyValues("mpple.mapper.type=dozer")
            .run(context -> assertThat(context).doesNotHaveBean(ModelMapper.class));
    }
}
package ml.wonwoo.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.autoconfigure.modelmapper.ModelMapperCustomizer;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class MppleAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner().withConfiguration(
        AutoConfigurations.of(MppleAutoConfiguration.class));

    @Test
    public void registersAutomatically() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(ModelMapper.class);
            assertThat(context).getBeanNames(ModelMapperCustomizer.class).hasSize(2);
        });
    }
}
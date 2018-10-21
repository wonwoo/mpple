package ml.wonwoo.autoconfigure;

import ml.wonwoo.mapped.DefaultMapped;
import ml.wonwoo.mapped.Mapped;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(MapperCondition.class)
public class DefaultAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Mapped.class)
    public Mapped mapped() {
        return new DefaultMapped();
    }
}

package ml.wonwoo.autoconfigure;

import ml.wonwoo.autoconfigure.registrar.SpringBootMppledsRegistrar;
import ml.wonwoo.spring.EnableMppled;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean(annotation = EnableMppled.class)
public class MppledsRegistrarAutoConfiguration {

    @Import(SpringBootMppledsRegistrar.class)
    static class EnableMppledConfiguration {

    }
}

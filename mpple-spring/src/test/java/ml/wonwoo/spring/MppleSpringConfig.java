package ml.wonwoo.spring;

import ml.wonwoo.modelmapper.mapped.ModelMapperMapped;
import org.springframework.context.annotation.Bean;

@EnableMppled
public class MppleSpringConfig {

    @Bean
    ModelMapperMapped modelMapperMapped () {
        return new ModelMapperMapped();
    }
}

package ml.wonwoo.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;

class MapperCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String sourceClass = "";
        if (metadata instanceof ClassMetadata) {
            sourceClass = ((ClassMetadata) metadata).getClassName();
        }
        ConditionMessage.Builder message = ConditionMessage.forCondition("Mapper", sourceClass);
        MapperType specified = Binder.get(context.getEnvironment())
            .bind("mpple.mapper.type", MapperType.class)
            .orElseGet(() -> MapperType.NONE);
        MapperType storeType = MapperType.getType(((AnnotationMetadata) metadata).getClassName());
        if (specified == storeType) {
            return ConditionOutcome.match(message.because(specified + " mapper type"));
        }
        return ConditionOutcome.noMatch(message.because("unknown mapper type"));
    }
}

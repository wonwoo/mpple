package ml.wonwoo.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import ml.wonwoo.mapped.DefaultMapped;
import ml.wonwoo.mapped.Mapped;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mppled {

    Class<? extends Mapped> mappedClasses() default DefaultMapped.class;

    String mapped() default "";

}

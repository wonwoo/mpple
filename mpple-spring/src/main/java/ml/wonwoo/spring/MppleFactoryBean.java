package ml.wonwoo.spring;

import ml.wonwoo.Mpple;
import ml.wonwoo.mapped.Mapped;
import org.springframework.beans.factory.FactoryBean;

public class MppleFactoryBean implements FactoryBean<Object> {

    private Class<?> type;
    private Mapped mapped;

    @Override
    public Object getObject() {
        return Mpple.builder()
            .mapped(mapped)
            .target(type);
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public void setMapped(Mapped mapped) {
        this.mapped = mapped;
    }

    public Mapped getMapped() {
        return mapped;
    }
}

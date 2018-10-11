package ml.wonwoo;

import ml.wonwoo.core.Meca;
import ml.wonwoo.handler.Mapper;
import ml.wonwoo.mapped.Mapped;

public class Mpple {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Mapped mapped = Mapped.create();

        public <T> T target(Class<T> mapType) {
            return target(new Mapper<>(mapType));
        }

        public <T> T target(Mapper<T> mapper) {
            return build().newInstance(mapper);
        }

        public Builder mapped(Mapped mapped) {
            this.mapped = mapped;
            return this;
        }

        public Meca build() {
            return Meca.create(mapped);
        }
    }
}

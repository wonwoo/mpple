package ml.wonwoo.handler;

import java.util.Objects;

public class Mapper<T> {

    private final Class<T> type;

    public Mapper(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mapper<?> mapper = (Mapper<?>) o;
        return Objects.equals(type, mapper.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Mapper{" +
            "type=" + type +
            '}';
    }
}

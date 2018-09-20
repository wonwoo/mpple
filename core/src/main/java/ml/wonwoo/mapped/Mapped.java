package ml.wonwoo.mapped;

public interface Mapped {

    <D> D map(Object source, Class<D> type);

    static Mapped create() {
        return new DefaultMapped();
    }
}

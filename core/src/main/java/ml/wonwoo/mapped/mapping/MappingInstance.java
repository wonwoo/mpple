package ml.wonwoo.mapped.mapping;

public interface MappingInstance {

    <D> D map(Object source, Class<D> type);

}

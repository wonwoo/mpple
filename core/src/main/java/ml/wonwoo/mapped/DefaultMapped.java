package ml.wonwoo.mapped;

public class DefaultMapped implements Mapped {

    private MappingInstance mappingInstance = new MappingInstance();

    @Override
    public <D> D map(Object source, Class<D> type) {
        return mappingInstance.newInstance(source, type);
    }

    public void setMappingInstance(MappingInstance mappingInstance) {
        this.mappingInstance = mappingInstance;
    }
}
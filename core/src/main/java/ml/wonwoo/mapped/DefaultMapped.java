package ml.wonwoo.mapped;

import ml.wonwoo.mapped.mapping.MappingInstance;
import ml.wonwoo.mapped.mapping.MappingInstanceImpl;

public class DefaultMapped implements Mapped {

    private MappingInstance mappingInstance = new MappingInstanceImpl();

    @Override
    public <D> D map(Object source, Class<D> type) {
        return mappingInstance.map(source, type);
    }

    public void setMappingInstance(MappingInstance mappingInstance) {
        this.mappingInstance = mappingInstance;
    }
}
package ml.wonwoo.mapped;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DozerMapped implements Mapped {

    private Mapper mapper = new DozerBeanMapper();

    @Override
    public <D> D map(Object source, Class<D> type) {
        return mapper.map(source, type);
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}

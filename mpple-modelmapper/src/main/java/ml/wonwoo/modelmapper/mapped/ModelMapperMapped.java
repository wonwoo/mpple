package ml.wonwoo.modelmapper.mapped;

import ml.wonwoo.mapped.Mapped;
import org.modelmapper.ModelMapper;

public class ModelMapperMapped implements Mapped {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public <D> D map(Object source, Class<D> type) {
        return modelMapper.map(source, type);
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}

package ml.wonwoo.autoconfigure.modelmapper;

import org.modelmapper.ModelMapper;

@FunctionalInterface
public interface ModelMapperCustomizer {

    void customize(ModelMapper modelMapper);
}

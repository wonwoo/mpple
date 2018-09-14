package ml.wonwoo.autoconfigure.modelmapper;

import java.util.List;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class ConverterCustomizer implements ModelMapperCustomizer {

    private List<Converter<?, ?>> converters;

    public void setConverters(List<Converter<?, ?>> converters) {
        this.converters = converters;
    }

    @Override
    public void customize(ModelMapper modelMapper) {
        if (converters != null) {
            converters.forEach(modelMapper::addConverter);
        }
    }
}

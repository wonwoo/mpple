package ml.wonwoo.autoconfigure.modelmapper;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class PropertyMapCustomizer implements ModelMapperCustomizer {

    private List<PropertyMap<?, ?>> propertyMaps;

    public void setPropertyMaps(List<PropertyMap<?, ?>> propertyMaps) {
        this.propertyMaps = propertyMaps;
    }

    @Override
    public void customize(ModelMapper modelMapper) {
        if (propertyMaps != null) {
            propertyMaps.forEach(modelMapper::addMappings);
        }
    }
}

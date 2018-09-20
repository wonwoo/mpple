package ml.wonwoo.mapped;

import ml.wonwoo.mapped.converter.ArrayConverter;
import ml.wonwoo.mapped.converter.CollectionConverter;
import ml.wonwoo.mapped.converter.EnumConverter;
import ml.wonwoo.mapped.converter.MiscConverter;
import ml.wonwoo.mapped.converter.MapConverter;
import ml.wonwoo.mapped.converter.MappedConverter;
import ml.wonwoo.mapped.converter.MappedConverterComposite;
import ml.wonwoo.mapped.converter.WrapperTypeConverter;
import ml.wonwoo.util.ClassUtils;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

public class MappingInstance {

    private final MappedConverterComposite converter = new MappedConverterComposite();

    public MappingInstance() {
        this.defaultConverter();
    }

    @SuppressWarnings("unchecked")
    public <D> D newInstance(Object source, Class<D> type) {
        BeanCopier copier = BeanCopier.create(source.getClass(), type, true);
        D destination = ClassUtils.newInstance(type);
        if (ClassUtils.isWrapperType(type)) {
            return (D) source;
        }
        copier.copy(source, destination, new DefaultConverter(type, converter));
        return destination;
    }

    private class DefaultConverter implements Converter {

        private final Class<?> clazz;
        private final MappedConverter converter;

        private DefaultConverter(Class<?> clazz, MappedConverter converter) {
            this.clazz = clazz;
            this.converter = converter;
        }

        @Override
        public Object convert(Object value, Class target, Object context) {
            if (value == null) {
                return null;
            }
            if (converter.supports(target)) {
                return converter.convert(clazz, value, target, context);
            }
            return newInstance(value, target);
        }
    }

    private void defaultConverter() {
        this.converter.addConverters(
            new CollectionConverter(this),
            new MapConverter(this),
            new WrapperTypeConverter(),
            new ArrayConverter(this),
            new MiscConverter(),
            new EnumConverter());
    }

    public void addConverter(MappedConverter mappedConverter) {
        this.converter.addConverter(mappedConverter);
    }

}

package ml.wonwoo.mapped.mapping;

import ml.wonwoo.core.BeanCopyAnnotation;
import ml.wonwoo.mapped.converter.ArrayConverter;
import ml.wonwoo.mapped.converter.CollectionConverter;
import ml.wonwoo.mapped.converter.EnumConverter;
import ml.wonwoo.mapped.converter.MapConverter;
import ml.wonwoo.mapped.converter.MappedConverter;
import ml.wonwoo.mapped.converter.MappedConverterComposite;
import ml.wonwoo.mapped.converter.WrapperTypeConverter;
import ml.wonwoo.util.Assert;
import ml.wonwoo.util.ClassUtils;
import net.sf.cglib.beans.BeanCopier;

public class MappingInstanceImpl implements MappingInstance {

    private final MappedConverterComposite converter = new MappedConverterComposite();

    public MappingInstanceImpl() {
        this.defaultConverter();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <D> D map(Object source, Class<D> type) {
        BeanCopier copier = BeanCopyAnnotation.create(source.getClass(), type);
        D destination = ClassUtils.instantiateClass(type);
        if (!ClassUtils.isObject(type)) {
            return (D) source;
        }
        copier.copy(source, destination, (value, target, context) -> {
            if (value == null) {
                return null;
            }
            if (converter.supports(target)) {
                return converter.convert(type, value, target, context);
            }
            return this.map(value, target);
        });
        return destination;
    }

    private void defaultConverter() {
        this.converter.addConverters(
            new WrapperTypeConverter(),
            new CollectionConverter(this),
            new MapConverter(this),
            new ArrayConverter(this),
            new EnumConverter()
        );
    }

    public void addConverter(MappedConverter mappedConverter) {
        Assert.notNull(mappedConverter, "mappedConverter must not null");
        this.converter.addConverter(mappedConverter);
    }
}

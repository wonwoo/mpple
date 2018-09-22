package ml.wonwoo.mapped.converter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MappedConverterComposite implements MappedConverter {

    private final List<MappedConverter> mappedConverters = new LinkedList<>();
    private final Map<Class<?>, MappedConverter> converterCache = new ConcurrentHashMap<>(128);

    @Override
    public boolean supports(Class<?> target) {
        return converter(target) != null;
    }

    private MappedConverter converter(Class<?> target) {
        MappedConverter converter = converterCache.get(target);
        if (converter == null) {
            for (MappedConverter mappedConverter : mappedConverters) {
                if (mappedConverter.supports(target)) {
                    converter = mappedConverter;
                    converterCache.put(target, converter);
                    break;
                }
            }
        }
        return converter;
    }

    public MappedConverterComposite addConverters(MappedConverter... mappedConverter) {
        if (mappedConverter != null) {
            Collections.addAll(this.mappedConverters, mappedConverter);
        }
        return this;
    }


    public MappedConverterComposite addConverter(MappedConverter mappedConverter) {
        mappedConverters.add(mappedConverter);
        return this;
    }

    @Override
    public Object convert(Class<?> clazz, Object value, Class<?> target, Object context) {
        return converter(target).convert(clazz, value, target, context);
    }
}

package ml.wonwoo.mapped;

import ml.wonwoo.util.Assert;
import ml.wonwoo.util.ClassUtils;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public interface Mapped {

    <D> D map(Object source, Class<D> type);

    static Mapped create() {
        return new DefaultMapped();
    }

    class DefaultMapped implements Mapped {

        private final static Objenesis objenesis = new ObjenesisStd();
        private Converter converter = new DefaultConverter();

        @Override
        public <D> D map(Object source, Class<D> type) {
            return newInstance(source, type);
        }

        @SuppressWarnings("unchecked")
        <D> D newInstance(Object source, Class<D> type) {
            BeanCopier copier = BeanCopier.create(source.getClass(), type, this.converter != null);
            D destination = objenesis.newInstance(type);
            if (ClassUtils.isWrapperType(type)) {
                return (D) source;
            }
            copier.copy(source, destination, this.converter);
            return destination;
        }

        private class DefaultConverter implements Converter {

            @Override
            public Object convert(Object value, Class target, Object context) {
                if (ClassUtils.isWrapperType(target)) {
                    return value;
                }
                return newInstance(value, target);
            }
        }

        public void setConverter(Converter converter) {
            Assert.notNull(converter, "converter must not null");
            this.converter = converter;
        }
    }
}

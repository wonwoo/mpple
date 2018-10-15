package ml.wonwoo.core;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import ml.wonwoo.util.ClassUtils;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.ClassEmitter;
import net.sf.cglib.core.CodeEmitter;
import net.sf.cglib.core.Constants;
import net.sf.cglib.core.EmitUtils;
import net.sf.cglib.core.Local;
import net.sf.cglib.core.MethodInfo;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.core.TypeUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

abstract public class BeanCopyAnnotation {

    private static final Type CONVERTER =
        TypeUtils.parseType("net.sf.cglib.core.Converter");
    private static final Type BEAN_COPIER =
        TypeUtils.parseType("net.sf.cglib.beans.BeanCopier");
    private static final Signature COPY =
        new Signature("copy", Type.VOID_TYPE, new Type[]{Constants.TYPE_OBJECT, Constants.TYPE_OBJECT, CONVERTER});
    private static final Signature CONVERT =
        TypeUtils.parseSignature("Object convert(Object, Class, Object)");

    public static BeanCopier create(Class source, Class target) {
        Generator gen = new Generator();
        gen.setSource(source);
        gen.setTarget(target);
        gen.setUseConverter(true);
        return gen.create();
    }

    public static class Generator extends BeanCopier.Generator {

        private Class source;
        private Class target;

        public void setSource(Class source) {
            super.setSource(source);
            this.source = source;
        }

        public void setTarget(Class target) {
            super.setTarget(target);
            this.target = target;
        }

        public void generateClass(ClassVisitor v) {
            Type sourceType = Type.getType(source);
            Type targetType = Type.getType(target);
            ClassEmitter ce = new ClassEmitter(v);
            ce.begin_class(Constants.V1_2,
                Constants.ACC_PUBLIC,
                getClassName(),
                BEAN_COPIER,
                null,
                Constants.SOURCE_FILE);

            EmitUtils.null_constructor(ce);
            CodeEmitter e = ce.begin_method(Constants.ACC_PUBLIC, COPY, null);
            PropertyDescriptor[] getters = ReflectUtils.getBeanGetters(source);
            PropertyDescriptor[] setters = ReflectUtils.getBeanSetters(target);
            Field[] declaredFields = ClassUtils.findAllFields(target);

            Map<String, Field> fields = new HashMap<>();
            for (Field field : declaredFields) {
                fields.put(field.getName(), field);
            }

            Map<String, Object> names = new HashMap<>();
            for (PropertyDescriptor get : getters) {
                names.put(get.getName(), get);
            }
            Local targetLocal = e.make_local();
            Local sourceLocal = e.make_local();
            e.load_arg(1);
            e.checkcast(targetType);
            e.store_local(targetLocal);
            e.load_arg(0);
            e.checkcast(sourceType);
            e.store_local(sourceLocal);
            for (PropertyDescriptor setter : setters) {
                Method readMethod = setter.getReadMethod();
                Method writeMethod = setter.getWriteMethod();
                Mapping annotation = findMappingAnnotation(fields.get(setter.getName()));
                if(annotation == null) {
                    annotation = findMappingAnnotation(readMethod);
                    if (annotation == null) {
                        annotation = findMappingAnnotation(writeMethod);
                    }
                }
                String value;
                if (annotation != null) {
                    value = annotation.value();
                } else {
                    value = setter.getName();
                }
                PropertyDescriptor getter = (PropertyDescriptor) names.get(value);
                if (getter != null) {
                    MethodInfo read = ReflectUtils.getMethodInfo(getter.getReadMethod());
                    MethodInfo write = ReflectUtils.getMethodInfo(writeMethod);
                    Type setterType = write.getSignature().getArgumentTypes()[0];
                    e.load_local(targetLocal);
                    e.load_arg(2);
                    e.load_local(sourceLocal);
                    e.invoke(read);
                    e.box(read.getSignature().getReturnType());
                    EmitUtils.load_class(e, setterType);
                    e.push(write.getSignature().getName());
                    e.invoke_interface(CONVERTER, CONVERT);
                    e.unbox_or_zero(setterType);
                    e.invoke(write);
                }
            }
            e.return_value();
            e.end_method();
            ce.end_class();
        }
    }

    private static Mapping findMappingAnnotation(Method method) {
        return ClassUtils.findMappingAnnotation(method, Mapping.class);
    }

    private static Mapping findMappingAnnotation(Field field) {
        return ClassUtils.findMappingAnnotation(field, Mapping.class);
    }
}

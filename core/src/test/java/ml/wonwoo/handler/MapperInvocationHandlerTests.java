package ml.wonwoo.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Proxy;
import ml.wonwoo.mapped.DefaultMapped;
import ml.wonwoo.model.FooMapper;
import org.junit.Test;

public class MapperInvocationHandlerTests {

    private final MapperInvocationHandler mapperInvocationHandler =
        new MapperInvocationHandler(new Mapper<>(String.class), new DefaultMapped());

    @Test
    public void invokeTest() throws Throwable {
        Object result = mapperInvocationHandler.invoke(null,
            this.getClass().getMethod("hello", String.class), new Object[]{"wonwoo"});
        assertThat(result).isEqualTo("wonwoo");
    }

    @Test
    public void equalsTest() throws Throwable {
        Object result = mapperInvocationHandler.invoke(null,
            this.getClass().getMethod("equals", Object.class), new Object[]{"wonwoo"});
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void equalsInvocationHandlerTest() throws Throwable {
        Object result = mapperInvocationHandler.invoke(null,
            this.getClass().getMethod("equals", Object.class), new Object[]{
                Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{FooMapper.class}, mapperInvocationHandler)});
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void hashCodeTest() throws Throwable {
        Object result = mapperInvocationHandler.invoke(null,
            this.getClass().getMethod("hashCode"), new Object[]{});
        assertThat(result).isNotNull();
    }

    @Test
    public void toStringTest() throws Throwable {
        Object result = mapperInvocationHandler.invoke(null,
            this.getClass().getMethod("toString"), new Object[]{});
        assertThat(result).isNotNull();
    }

    @Test(expected = RuntimeException.class)
    public void noArgsTest() throws Throwable {
        mapperInvocationHandler.invoke(null,
            this.getClass().getMethod("hello"), new Object[]{});
    }

    @Test(expected = RuntimeException.class)
    public void twoArgsTest() throws Throwable {
        mapperInvocationHandler.invoke(null,
            this.getClass().getMethod("hello", String.class, String.class), new Object[]{});
    }

    public String hello() {
        return "hello ";
    }

    public String hello(String name) {
        return "hello " + name;
    }

    public String hello(String n, String m) {
        return "hello ";
    }
}
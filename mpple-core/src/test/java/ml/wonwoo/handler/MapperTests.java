package ml.wonwoo.handler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MapperTests {

    @Test
    public void mapperEquals() {
        Mapper<String> mapper = new Mapper<>(String.class);
        Mapper<String> mapper1 = new Mapper<>(String.class);
        assertThat(mapper).isEqualTo(mapper1);
    }

    @Test
    public void mapperNotEquals() {
        Mapper<String> mapper = new Mapper<>(String.class);
        Mapper<Integer> mapper1 = new Mapper<>(Integer.class);
        assertThat(mapper).isNotEqualTo(mapper1);
    }

    @Test
    public void mapperHashCode() {
        Mapper<String> mapper = new Mapper<>(String.class);
        Mapper<String> mapper1 = new Mapper<>(String.class);
        assertThat(mapper.hashCode()).isEqualTo(mapper1.hashCode());
    }
}
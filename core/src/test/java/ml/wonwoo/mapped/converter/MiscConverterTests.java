package ml.wonwoo.mapped.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.chrono.JapaneseDate;
import ml.wonwoo.model.Foo;
import org.junit.Test;

public class MiscConverterTests {

    private final MiscConverter converter = new MiscConverter();

    @Test
    public void supports() {
        assertThat(converter.supports(LocalDate.class)).isTrue();
        assertThat(converter.supports(LocalDateTime.class)).isTrue();
        assertThat(converter.supports(Instant.class)).isTrue();
        assertThat(converter.supports(JapaneseDate.class)).isTrue();
        assertThat(converter.supports(OffsetDateTime.class)).isTrue();
        assertThat(converter.supports(Foo.class)).isFalse();
    }

    @Test
    public void convert() {
        assertThat(converter.convert(null, LocalDateTime.now(), null, null))
            .isNotNull();
    }

}
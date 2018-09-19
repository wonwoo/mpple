package ml.wonwoo;

import java.util.concurrent.TimeUnit;
import ml.wonwoo.modelmapper.mapped.ModelMapperMapped;
import org.modelmapper.ModelMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

//TODO

@Fork(value = 1, warmups = 1)
@State(Scope.Benchmark)
public class BenchmarkTest {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Param({"10", "100", "1000"})
    public int iterations;

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void defaultMapping() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        forFoo(fooMapper);
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void modelMapperMappedMapping() {
        FooMapper fooMapper = Mpple.builder()
            .mapped(new ModelMapperMapped())
            .target(FooMapper.class);
        forFoo(fooMapper);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void modelMapperMapping() {
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < iterations; i++) {
            Foo foo = new Foo();
            foo.setFirstName("wonwoo");
            foo.setLastName("lee");
            modelMapper.map(foo, FooDto.class);
        }
    }

    private void forFoo(FooMapper fooMapper) {
        for (int i = 0; i < iterations; i++) {
            Foo foo = new Foo();
            foo.setFirstName("wonwoo");
            foo.setLastName("lee");
            fooMapper.foo(foo);
        }
    }
}

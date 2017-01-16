package primitive.set;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TestContainsLongInSets {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void testLongSetOracle() {
        Set<Long> set = new HashSet<>();
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    @Benchmark
    public void testHPPCLongSet() {
        com.carrotsearch.hppc.LongSet set = new com.carrotsearch.hppc.LongHashSet();
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    @Benchmark
    public void testKolobokLongSetQ() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl().newMutableSet();
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    @Benchmark
    public void testKolobokLongSetL() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl().newMutableSet();
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestContainsLongInSets.class.getSimpleName())
                .param("size","30000","50000","100000")
                .build();

        new Runner(opt).run();
    }
}

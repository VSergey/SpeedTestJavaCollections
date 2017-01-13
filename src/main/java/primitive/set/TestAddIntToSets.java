package primitive.set;

import com.koloboke.collect.set.IntSet;
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
public class TestAddIntToSets {
    private static final int SIZE = 30000;

    @Benchmark
    public void testIntSetOracle() {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < SIZE; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < SIZE; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void testHPPCIntHashSet() {
        com.carrotsearch.hppc.IntHashSet set = new com.carrotsearch.hppc.IntHashSet();
        for(int i = 0; i < SIZE; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < SIZE; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void testApachiIntHashSet() {
        org.eclipse.collections.impl.set.mutable.primitive.IntHashSet set = new org.eclipse.collections.impl.set.mutable.primitive.IntHashSet();
        for(int i = 0; i < SIZE; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < SIZE; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void testKolobokHashIntSetL() {
        IntSet set = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < SIZE; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < SIZE; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void testKolobokHashIntSetQ() {
        IntSet set = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < SIZE; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < SIZE; i++) {
            set.add(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddIntToSets.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}

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
public class TestContainsIntInSets {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void testIntSetOracle() {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    @Benchmark
    public void testHPPCIntHashSet() {
        com.carrotsearch.hppc.IntHashSet set = new com.carrotsearch.hppc.IntHashSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    @Benchmark
    public void testApachiIntHashSet() {
        org.eclipse.collections.impl.set.mutable.primitive.IntHashSet set = new org.eclipse.collections.impl.set.mutable.primitive.IntHashSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    @Benchmark
    public void testKolobokHashIntSetL() {
        IntSet set = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    @Benchmark
    public void testKolobokHashIntSetQ() {
        IntSet set = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestContainsIntInSets.class.getSimpleName())
                .param("size","30000","50000","100000")
                .build();

        new Runner(opt).run();
    }

}

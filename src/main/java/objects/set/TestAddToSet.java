package objects.set;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TestAddToSet {
    private static final int SIZE = 30000;

    private void fillSet(Set<String> set) {
        for(int i = 0; i < SIZE; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < SIZE; i++) {
            set.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void testOracleHashSet() {
        Set<String> set = new HashSet<>();
        fillSet(set);
    }

    @Benchmark
    public void testOracleTreeSet() {
        Set<String> set = new TreeSet<>();
        fillSet(set);
    }

    @Benchmark
    public void testOracleLinkedSet() {
        Set<String> set = new LinkedHashSet<>();
        fillSet(set);
    }

    @Benchmark
    public void testEclipseUnifiedSet() {
        Set<String> set = new UnifiedSet<>();
        fillSet(set);
    }

    @Benchmark
    public void testEclipseTreeSortedSet() {
        Set<String> set = new TreeSortedSet<>();
        fillSet(set);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToSet.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}

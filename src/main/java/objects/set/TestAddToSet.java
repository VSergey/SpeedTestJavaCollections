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

    private void fillSet(Set<String> map) {
        for(int i = 0; i < SIZE; i+=2) {
            map.add(Integer.toString(i));
        }
        for(int i = 0; i < SIZE; i++) {
            map.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void testOracleHashMap() {
        Set<String> map = new HashSet<>();
        fillSet(map);
    }

    @Benchmark
    public void testOracleTreeMap() {
        Set<String> map = new TreeSet<>();
        fillSet(map);
    }

    @Benchmark
    public void testOracleLinkedMap() {
        Set<String> map = new LinkedHashSet<>();
        fillSet(map);
    }

    @Benchmark
    public void testEclipseUnifiedSet() {
        Set<String> map = new UnifiedSet<>();
        fillSet(map);
    }

    @Benchmark
    public void testEclipseTreeSortedSet() {
        Set<String> map = new TreeSortedSet<>();
        fillSet(map);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToSet.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}

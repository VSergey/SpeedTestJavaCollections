package objects.set;

import com.carrotsearch.hppc.*;
import gnu.trove.set.hash.*;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
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
public class TestAddToSets {
    @Param({"30000"})
    private int size;

    private void fillSet(Set<String> set) {
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Oracle_HashSet() {
        Set<String> set = new HashSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        Set<String> set = new TreeSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_Oracle_LinkedSet() {
        Set<String> set = new LinkedHashSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_Eclipse_UnifiedSet() {
        Set<String> set = new UnifiedSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_Eclipse_TreeSortedSet() {
        Set<String> set = new TreeSortedSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_Hppc_ObjectHashSet() {
        ObjectSet<String> set = new ObjectHashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Hppc_ObjectScatterSet() {
        ObjectSet<String> set = new ObjectScatterSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_FastUtil_ObjectOpenHashSet() {
        ObjectOpenHashSet<String> set = new ObjectOpenHashSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_FastUtil_ObjectOpenHashBigSet() {
        ObjectOpenHashBigSet<String> set = new ObjectOpenHashBigSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_Trove_THashSet() {
        THashSet<String> set = new THashSet<>();
        fillSet(set);
    }

    @Benchmark
    public void test_Trove_TLinkedHashSet() {
        TLinkedHashSet<String> set = new TLinkedHashSet<>();
        fillSet(set);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

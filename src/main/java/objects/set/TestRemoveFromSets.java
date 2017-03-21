package objects.set;

import com.carrotsearch.hppc.*;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TLinkedHashSet;
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
public class TestRemoveFromSets {
    @Param({"30000"})
    private int size;
    private Set<String> set1;
    private Set<String> set2;
    private Set<String> set3;
    private Set<String> set4;
    private Set<String> set5;
    private ObjectHashSet<String> set6;
    private ObjectScatterSet<String> set7;
    private ObjectOpenHashSet<String> set8;
    private ObjectOpenHashBigSet<String> set9;
    private THashSet<String> set10;

    @Benchmark
    public void test_Oracle_HashSet() {
        for(int i = 1; i < size; i+=3) {
            set1.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        for(int i = 1; i < size; i+=3) {
            set2.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Oracle_LinkedSet() {
        for(int i = 1; i < size; i+=3) {
            set3.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Eclipse_UnifiedSet() {
        for(int i = 1; i < size; i+=3) {
            set4.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Eclipse_TreeSortedSet() {
        for(int i = 1; i < size; i+=3) {
            set5.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Hppc_ObjectHashSet() {
        for(int i = 1; i < size; i+=3) {
            set6.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Hppc_ObjectScatterSet() {
        for(int i = 1; i < size; i+=3) {
            set7.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_FastUtil_ObjectOpenHashSet() {
        for(int i = 1; i < size; i+=3) {
            set8.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_FastUtil_ObjectOpenHashBigSet() {
        for(int i = 1; i < size; i+=3) {
            set9.remove(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Trove_THashSet() {
        for(int i = 1; i < size; i+=3) {
            set10.remove(Integer.toString(i));
        }
    }

    @Setup(Level.Iteration)
    public void setup() {
        set1 = new HashSet<>(size);
        set2 = new TreeSet<>();
        set3 = new LinkedHashSet<>();
        set4 = new UnifiedSet<>(size);
        set5 = new TreeSortedSet<>();
        set6 = new ObjectHashSet<>(size);
        set7 = new ObjectScatterSet<>(size);
        set8 = new ObjectOpenHashSet<>(size);
        set9 = new ObjectOpenHashBigSet<>(size);
        set10 = new THashSet<>(size);

        for(int i = 0; i < size; i++) {
            String s = Integer.toString(i);
            set1.add(s);
            set2.add(s);
            set3.add(s);
            set4.add(s);
            set5.add(s);
            set6.add(s);
            set7.add(s);
            set8.add(s);
            set9.add(s);
            set10.add(s);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestRemoveFromSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

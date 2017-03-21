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
public class TestContainsSets {
    @Param({"30000"})
    private int size;
    private Set<String> set1;
    private Set<String> set2;
    private Set<String> set3;
    private Set<String> set4;
    private Set<String> set5;
    private ObjectSet<String> set6;
    private ObjectSet<String> set7;
    private ObjectOpenHashSet<String> set8;
    private ObjectOpenHashBigSet<String> set9;
    private THashSet<String> set10;
    private TLinkedHashSet<String> set11;

    @Benchmark
    public int test_Oracle_HashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set1.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Oracle_TreeSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set2.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Oracle_LinkedSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set3.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Eclipse_UnifiedSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set4.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Eclipse_TreeSortedSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set5.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Hppc_ObjectHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set6.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Hppc_ObjectScatterSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set7.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_FastUtil_ObjectOpenHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set8.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_FastUtil_ObjectOpenHashBigSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set9.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Trove_THashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set10.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Benchmark
    public int test_Trove_TLinkedHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set11.contains(Integer.toString(i))) count++;
        }
        return count;
    }

    @Setup(Level.Trial)
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
        set11 = new TLinkedHashSet<>(size);

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
            set11.add(s);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestContainsSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

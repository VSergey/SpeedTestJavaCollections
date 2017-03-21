package objects.set;

import com.carrotsearch.hppc.*;
import com.carrotsearch.hppc.procedures.ObjectProcedure;
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
public class TestIterateSets {
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
    public long test_Oracle_HashSet() {
        final long sum[] = new long[1];
        set1.forEach(s -> sum[0]+=s.hashCode());
        return sum[0];
    }

    @Benchmark
    public long test_Oracle_TreeSet() {
        final long sum[] = new long[1];
        set2.forEach(s -> sum[0]+=s.hashCode());
        return sum[0];
    }

    @Benchmark
    public long test_Oracle_LinkedSet() {
        final long sum[] = new long[1];
        set3.forEach(s -> sum[0]+=s.hashCode());
        return sum[0];
    }

    @Benchmark
    public long test_Eclipse_UnifiedSet() {
        final long sum[] = new long[1];
        set4.forEach(s -> sum[0]+=s.hashCode());
        return sum[0];
    }

    @Benchmark
    public long test_Eclipse_TreeSortedSet() {
        final long sum[] = new long[1];
        set5.forEach(s -> sum[0]+=s.hashCode());
        return sum[0];
    }

    @Benchmark
    public long test_Hppc_ObjectHashSet() {
        return set6.forEach(new ObjectProcedure<String>() {
            long sum;

            public void apply(String value) {
                sum+=value.hashCode();
            }
        }).sum;
    }

    @Benchmark
    public long test_Hppc_ObjectScatterSet() {
        return set7.forEach(new ObjectProcedure<String>() {
            long sum;

            public void apply(String value) {
                sum+=value.hashCode();
            }
        }).sum;
    }

    @Benchmark
    public long test_FastUtil_ObjectOpenHashSet() {
        final long sum[] = new long[1];
        set8.forEach(s -> sum[0]+=s.hashCode());
        return sum[0];
    }

    @Benchmark
    public long test_FastUtil_ObjectOpenHashBigSet() {
        final long sum[] = new long[1];
        set9.forEach(s -> sum[0]+=s.hashCode());
        return sum[0];
    }

    @Benchmark
    public long test_Trove_THashSet() {
        final long sum[] = new long[1];
        set10.forEach(s -> {
            sum[0]+=s.hashCode();
            return true;
        });
        return sum[0];
    }

    @Benchmark
    public long test_Trove_TLinkedHashSet() {
        final long sum[] = new long[1];
        set11.forEach(s -> {
            sum[0]+=s.hashCode();
            return true;
        });
        return sum[0];
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
                .include(TestIterateSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

package primitive.set.set4int;

import com.carrotsearch.hppc.procedures.IntProcedure;
import com.koloboke.collect.impl.hash.*;
import gnu.trove.set.hash.TIntHashSet;
import it.unimi.dsi.fastutil.ints.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TestIterateIntSets {
    @Param({"30000"})
    private int size;
    private Set<Integer> set1;
    private Set<Integer> set2;
    private Set<Integer> set3;
    private com.carrotsearch.hppc.IntHashSet set4;
    private com.carrotsearch.hppc.IntHashSet set5;
    private org.eclipse.collections.impl.set.mutable.primitive.IntHashSet set6;
    private IntOpenHashSet set7;
    private IntOpenHashBigSet set8;
    private IntAVLTreeSet set9;
    private IntLinkedOpenHashSet set10;
    private IntRBTreeSet set11;
    private MutableLHashIntSetGO set12;
    private MutableQHashIntSetGO set13;
    private UpdatableLHashIntSetGO set14;
    private UpdatableQHashIntSetGO set15;
    private TIntHashSet set16;

    @Benchmark
    public long test_Oracle_HashSet() {
        final long sum[] = new long[1];
        set1.forEach(i -> sum[0]+=i);
        return sum[0];
    }

    @Benchmark
    public long test_Oracle_LinkedHashSet() {
        final long sum[] = new long[1];
        set2.forEach(i -> sum[0]+=i);
        return sum[0];
    }

    @Benchmark
    public long test_Oracle_TreeSet() {
        final long sum[] = new long[1];
        set3.forEach(i -> sum[0]+=i);
        return sum[0];
    }

    @Benchmark
    public long test_HPPC_IntHashSet() {
        return set4.forEach(new IntProcedure() {
            long sum;

            public void apply(int value) {
                sum+=value;
            }
        }).sum;
    }

    @Benchmark
    public long test_HPPC_IntScatterSet() {
        return set5.forEach(new IntProcedure() {
            long sum;

            public void apply(int value) {
                sum+=value;
            }
        }).sum;
    }

    @Benchmark
    public long test_Eclipse_IntHashSet() {
        final long sum[] = new long[1];
        set6.forEach(i -> sum[0]+=i);
        return sum[0];
    }

    @Benchmark
    public long test_Fastutil_IntOpenHashSet() {
        long sum = 0;
        for(IntIterator it = set7.iterator(); it.hasNext(); ) {
            sum += it.nextInt();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_IntOpenHashBigSet() {
        long sum = 0;
        for(IntIterator it = set8.iterator(); it.hasNext(); ) {
            sum += it.nextInt();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_IntAVLTreeSet() {
        long sum = 0;
        for(IntIterator it = set9.iterator(); it.hasNext(); ) {
            sum += it.nextInt();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_IntLinkedOpenHashSet() {
        long sum = 0;
        for(IntIterator it = set10.iterator(); it.hasNext(); ) {
            sum += it.nextInt();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_IntRBTreeSet() {
        long sum = 0;
        for(IntIterator it = set11.iterator(); it.hasNext(); ) {
            sum += it.nextInt();
        }
        return sum;
    }

    @Benchmark
    public long test_Koloboke_MutableLHashIntSet() {
        final long sum[] = new long[1];
        set12.forEach((IntConsumer) value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Koloboke_MutableQHashIntSet() {
        final long sum[] = new long[1];
        set13.forEach((IntConsumer) value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Koloboke_UpdatableLHashIntSet() {
        final long sum[] = new long[1];
        set14.forEach((IntConsumer) value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Koloboke_UpdatableQHashIntSet() {
        final long sum[] = new long[1];
        set15.forEach((IntConsumer) value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Trove_TIntHashSet() {
        final long sum[] = new long[1];
        set16.forEach(value -> {
            sum[0]+=value;
            return true;
        });
        return sum[0];
    }

    @Setup(Level.Trial)
    public void setup() {
        set1 = new HashSet<>(size);
        set2 = new LinkedHashSet<>(size);
        set3 = new TreeSet<>();
        set4 = new com.carrotsearch.hppc.IntHashSet(size);
        set5 = new com.carrotsearch.hppc.IntScatterSet(size);
        set6 = new org.eclipse.collections.impl.set.mutable.primitive.IntHashSet(size);
        set7 = new IntOpenHashSet(size);
        set8 = new IntOpenHashBigSet(size);
        set9 = new IntAVLTreeSet();
        set10 = new IntLinkedOpenHashSet(size);
        set11 = new IntRBTreeSet();
        set16 = new TIntHashSet(size);
        set12 = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newMutableSet();
        set13 = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newMutableSet();
        set14 = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newUpdatableSet();
        set15 = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newUpdatableSet();

        for(int i = 0; i < size; i++) {
            set1.add(i);
            set2.add(i);
            set3.add(i);
            set4.add(i);
            set5.add(i);
            set6.add(i);
            set7.add(i);
            set8.add(i);
            set9.add(i);
            set10.add(i);
            set11.add(i);
            set12.add(i);
            set13.add(i);
            set14.add(i);
            set15.add(i);
            set16.add(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestIterateIntSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

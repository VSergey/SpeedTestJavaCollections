package primitive.set.set4long;

import com.carrotsearch.hppc.procedures.LongProcedure;
import com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl;
import com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl;
import gnu.trove.set.hash.TLongHashSet;
import it.unimi.dsi.fastutil.longs.*;
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
public class TestIterateLongSets {
    @Param({"30000"})
    private int size;
    private Set<Long> set1;
    private Set<Long> set2;
    private Set<Long> set3;
    private com.carrotsearch.hppc.LongSet set4;
    private com.carrotsearch.hppc.LongSet set5;
    private com.koloboke.collect.set.LongSet set6;
    private com.koloboke.collect.set.LongSet set7;
    private com.koloboke.collect.set.LongSet set8;
    private com.koloboke.collect.set.LongSet set9;
    private TLongHashSet set10;
    private org.eclipse.collections.impl.set.mutable.primitive.LongHashSet set11;
    private LongOpenHashSet set12;
    private LongOpenHashBigSet set13;
    private LongAVLTreeSet set14;
    private LongLinkedOpenHashSet set15;
    private LongRBTreeSet set16;

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
    public long test_HPPC_LongHashSet() {
        return set4.forEach(new LongProcedure() {
            long sum;

            public void apply(long value) {
                sum+=value;
            }
        }).sum;
    }

    @Benchmark
    public long test_HPPC_LongScatterSet() {
        return set5.forEach(new LongProcedure() {
            long sum;

            public void apply(long value) {
                sum+=value;
            }
        }).sum;
    }

    @Benchmark
    public long test_Koloboke_MutableLongSetQ() {
        final long sum[] = new long[1];
        set6.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Koloboke_MutableLongSetL() {
        final long sum[] = new long[1];
        set7.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Koloboke_UpdatableLongSetL() {
        final long sum[] = new long[1];
        set8.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Koloboke_UpdatableLongSetQ() {
        final long sum[] = new long[1];
        set9.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0];
    }

    @Benchmark
    public long test_Trove_TLongHashSet() {
        final long sum[] = new long[1];
        set10.forEach(value -> {
            sum[0]+=value;
            return true;
        });
        return sum[0];
    }

    @Benchmark
    public long test_Eclipse_LongHashSet() {
        final long sum[] = new long[1];
        set11.forEach(i -> sum[0]+=i);
        return sum[0];
    }

    @Benchmark
    public long test_Fastutil_LongOpenHashSet() {
        long sum = 0;
        for(LongIterator it = set12.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_LongOpenHashBigSet() {
        long sum = 0;
        for(LongIterator it = set13.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_LongAVLTreeSet() {
        long sum = 0;
        for(LongIterator it = set14.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_LongLinkedOpenHashSet() {
        long sum = 0;
        for(LongIterator it = set15.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum;
    }

    @Benchmark
    public long test_Fastutil_LongRBTreeSet() {
        long sum = 0;
        for(LongIterator it = set16.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum;
    }

    @Setup(Level.Trial)
    public void setup() {
        set1 = new HashSet<>(size);
        set2 = new LinkedHashSet<>(size);
        set3 = new TreeSet<>();
        set4 = new com.carrotsearch.hppc.LongHashSet(size);
        set5 = new com.carrotsearch.hppc.LongScatterSet(size);
        set6 = new QHashLongSetFactoryImpl().newMutableSet(size);
        set7 = new LHashLongSetFactoryImpl().newMutableSet(size);
        set8 = new QHashLongSetFactoryImpl().newUpdatableSet(size);
        set9 = new LHashLongSetFactoryImpl().newUpdatableSet(size);
        set10 = new TLongHashSet(size);
        set11 = new org.eclipse.collections.impl.set.mutable.primitive.LongHashSet(size);
        set12 = new LongOpenHashSet(size);
        set13 = new LongOpenHashBigSet(size);
        set14 = new LongAVLTreeSet();
        set15 = new LongLinkedOpenHashSet(size);
        set16 = new LongRBTreeSet();

        for(long i = 0; i < size; i++) {
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
                .include(TestIterateLongSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

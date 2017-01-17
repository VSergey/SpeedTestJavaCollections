package primitive.set;

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
public class TestAddLongToSets {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashSet() {
        Set<Long> set = new HashSet<>();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashSet() {
        Set<Long> set = new LinkedHashSet<>();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        Set<Long> set = new TreeSet<>();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_HPPC_LongHashSet() {
        com.carrotsearch.hppc.LongSet set = new com.carrotsearch.hppc.LongHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_HPPC_LongScatterSet() {
        com.carrotsearch.hppc.LongSet set = new com.carrotsearch.hppc.LongScatterSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLongSetQ() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl().newMutableSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLongSetL() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl().newMutableSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableLongSetL() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl().newUpdatableSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableLongSetQ() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl().newUpdatableSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Trove_TLongHashSet() {
        TLongHashSet set = new TLongHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Eclipse_LongHashSet() {
        org.eclipse.collections.impl.set.mutable.primitive.LongHashSet set = new org.eclipse.collections.impl.set.mutable.primitive.LongHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongOpenHashSet() {
        LongOpenHashSet set = new LongOpenHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongOpenHashBigSet() {
        LongOpenHashBigSet set = new LongOpenHashBigSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongAVLTreeSet() {
        LongAVLTreeSet set = new LongAVLTreeSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongLinkedOpenHashSet() {
        LongLinkedOpenHashSet set = new LongLinkedOpenHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongRBTreeSet() {
        LongRBTreeSet set = new LongRBTreeSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddLongToSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

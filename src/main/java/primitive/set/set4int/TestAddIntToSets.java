package primitive.set.set4int;

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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TestAddIntToSets {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashSet() {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashSet() {
        Set<Integer> set = new LinkedHashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        Set<Integer> set = new TreeSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_HPPC_IntHashSet() {
        com.carrotsearch.hppc.IntHashSet set = new com.carrotsearch.hppc.IntHashSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_HPPC_IntScatterSet() {
        com.carrotsearch.hppc.IntHashSet set = new com.carrotsearch.hppc.IntScatterSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Eclipse_IntHashSet() {
        org.eclipse.collections.impl.set.mutable.primitive.IntHashSet set = new org.eclipse.collections.impl.set.mutable.primitive.IntHashSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntOpenHashSet() {
        IntOpenHashSet set = new IntOpenHashSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntOpenHashBigSet() {
        IntOpenHashBigSet set = new IntOpenHashBigSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntAVLTreeSet() {
        IntAVLTreeSet set = new IntAVLTreeSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntLinkedOpenHashSet() {
        IntLinkedOpenHashSet set = new IntLinkedOpenHashSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntRBTreeSet() {
        IntRBTreeSet set = new IntRBTreeSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLHashIntSet() {
        MutableLHashIntSetGO set = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableQHashIntSet() {
        MutableQHashIntSetGO set = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableLHashIntSet() {
        UpdatableLHashIntSetGO set = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newUpdatableSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableQHashIntSet() {
        UpdatableQHashIntSetGO set = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newUpdatableSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    @Benchmark
    public void test_Trove_TIntHashSet() {
        TIntHashSet set = new TIntHashSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddIntToSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

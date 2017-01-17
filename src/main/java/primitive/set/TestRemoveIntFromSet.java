package primitive.set;

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
public class TestRemoveIntFromSet {
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
    private TIntHashSet set16;

    @Benchmark
    public void test_Oracle_HashSet() {
        for(int i = 1; i < size; i+=3) {
            set1.remove(i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashSet() {
        for(int i = 1; i < size; i+=3) {
            set2.remove(i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        for(int i = 1; i < size; i+=3) {
            set3.remove(i);
        }
    }

    @Benchmark
    public void test_HPPC_IntHashSet() {
        for(int i = 1; i < size; i+=3) {
            set4.remove(i);
        }
    }

    @Benchmark
    public void test_HPPC_IntScatterSet() {
        for(int i = 1; i < size; i+=3) {
            set5.remove(i);
        }
    }

    @Benchmark
    public void test_Eclipse_IntHashSet() {
        for(int i = 1; i < size; i+=3) {
            set6.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntOpenHashSet() {
        for(int i = 1; i < size; i+=3) {
            set7.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntOpenHashBigSet() {
        for(int i = 1; i < size; i+=3) {
            set8.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntAVLTreeSet() {
        for(int i = 1; i < size; i+=3) {
            set9.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntLinkedOpenHashSet() {
        for(int i = 1; i < size; i+=3) {
            set10.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_IntRBTreeSet() {
        for(int i = 1; i < size; i+=3) {
            set11.remove(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLHashIntSet() {
        for(int i = 1; i < size; i+=3) {
            set12.removeInt(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableQHashIntSet() {
        for(int i = 1; i < size; i+=3) {
            set13.removeInt(i);
        }
    }

    @Benchmark
    public void test_Trove_TIntHashSet() {
        for(int i = 1; i < size; i+=3) {
            set16.remove(i);
        }
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
        set12 = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newMutableSet();
        set13 = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newMutableSet();
        set16 = new TIntHashSet(size);

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
            set16.add(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestRemoveIntFromSet.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

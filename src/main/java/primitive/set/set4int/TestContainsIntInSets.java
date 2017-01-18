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
public class TestContainsIntInSets {
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
    public void test_Oracle_HashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set1.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set2.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set3.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_HPPC_IntHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set4.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_HPPC_IntScatterSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set5.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Eclipse_IntHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set6.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_IntOpenHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set7.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_IntOpenHashBigSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set8.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_IntAVLTreeSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set9.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_IntLinkedOpenHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set10.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_IntRBTreeSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set11.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLHashIntSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set12.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_MutableQHashIntSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set13.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableLHashIntSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set14.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableQHashIntSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set15.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Trove_TIntHashSet() {
        int count = 0;
        for(int i = 1; i < size; i+=3) {
            if(set16.contains(i)) count++;
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
                .include(TestContainsIntInSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

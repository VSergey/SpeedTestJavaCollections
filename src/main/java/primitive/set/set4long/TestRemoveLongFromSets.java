package primitive.set.set4long;

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
public class TestRemoveLongFromSets {
    @Param({"30000"})
    private int size;
    private Set<Long> set1;
    private Set<Long> set2;
    private Set<Long> set3;
    private com.carrotsearch.hppc.LongHashSet set4;
    private com.carrotsearch.hppc.LongScatterSet set5;
    private com.koloboke.collect.set.LongSet set6;
    private com.koloboke.collect.set.LongSet set7;
    private TLongHashSet set10;
    private org.eclipse.collections.impl.set.mutable.primitive.LongHashSet set11;
    private LongOpenHashSet set12;
    private LongOpenHashBigSet set13;
    private LongAVLTreeSet set14;
    private LongLinkedOpenHashSet set15;
    private LongRBTreeSet set16;

    @Benchmark
    public void test_Oracle_HashSet() {
        for(long i = 1; i < size; i+=3) {
            set1.remove(i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashSet() {
        for(long i = 1; i < size; i+=3) {
            set2.remove(i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        for(long i = 1; i < size; i+=3) {
            set3.remove(i);
        }
    }

    @Benchmark
    public void test_HPPC_LongHashSet() {
        for(long i = 1; i < size; i+=3) {
            set4.remove(i);
        }
    }

    @Benchmark
    public void test_HPPC_LongScatterSet() {
        for(long i = 1; i < size; i+=3) {
            set5.remove(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLongSetQ() {
        for(long i = 1; i < size; i+=3) {
            set6.removeLong(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLongSetL() {
        for(long i = 1; i < size; i+=3) {
            set7.removeLong(i);
        }
    }

    @Benchmark
    public void test_Trove_TLongHashSet() {
        for(long i = 1; i < size; i+=3) {
            set10.remove(i);
        }
    }

    @Benchmark
    public void test_Eclipse_LongHashSet() {
        for(long i = 1; i < size; i+=3) {
            set11.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongOpenHashSet() {
        for(long i = 1; i < size; i+=3) {
            set12.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongOpenHashBigSet() {
        for(long i = 1; i < size; i+=3) {
            set13.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongAVLTreeSet() {
        for(long i = 1; i < size; i+=3) {
            set14.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongLinkedOpenHashSet() {
        for(long i = 1; i < size; i+=3) {
            set15.remove(i);
        }
    }

    @Benchmark
    public void test_Fastutil_LongRBTreeSet() {
        for(long i = 1; i < size; i+=3) {
            set16.remove(i);
        }
    }

    @Setup(Level.Iteration)
    public void setup() {
        set1 = new HashSet<>(size);
        set2 = new LinkedHashSet<>(size);
        set3 = new TreeSet<>();
        set4 = new com.carrotsearch.hppc.LongHashSet(size);
        set5 = new com.carrotsearch.hppc.LongScatterSet(size);
        set6 = new QHashLongSetFactoryImpl().newMutableSet(size);
        set7 = new LHashLongSetFactoryImpl().newMutableSet(size);
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
            set10.add(i);
            set11.add(i);
            set12.add(i);
            set13.add(i);
            set16.add(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestRemoveLongFromSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

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
public class TestContainsLongInSets {
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
    public void test_Oracle_HashSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set1.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set2.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Oracle_TreeSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set3.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_HPPC_LongHashSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set4.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_HPPC_LongScatterSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set5.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLongSetQ() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set6.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_MutableLongSetL() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set7.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableLongSetL() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set8.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableLongSetQ() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set9.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Trove_TLongHashSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set10.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Eclipse_LongHashSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set11.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_LongOpenHashSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set12.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_LongOpenHashBigSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set13.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_LongAVLTreeSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set14.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_LongLinkedOpenHashSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set15.contains(i)) count++;
        }
    }

    @Benchmark
    public void test_Fastutil_LongRBTreeSet() {
        int count = 0;
        for(long i = 1; i < size; i+=3) {
            if(set16.contains(i)) count++;
        }
    }

    @Setup(Level.Trial)
    public void setup() {
        set1 = new HashSet<>();
        set2 = new LinkedHashSet<>();
        set3 = new TreeSet<>();
        set4 = new com.carrotsearch.hppc.LongHashSet();
        set5 = new com.carrotsearch.hppc.LongScatterSet();
        set6 = new QHashLongSetFactoryImpl().newMutableSet();
        set7 = new LHashLongSetFactoryImpl().newMutableSet();
        set8 = new QHashLongSetFactoryImpl().newUpdatableSet();
        set9 = new LHashLongSetFactoryImpl().newUpdatableSet();
        set10 = new TLongHashSet();
        set11 = new org.eclipse.collections.impl.set.mutable.primitive.LongHashSet();
        set12 = new LongOpenHashSet();
        set13 = new LongOpenHashBigSet();
        set14 = new LongAVLTreeSet();
        set15 = new LongLinkedOpenHashSet();
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
            set16.add(i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestContainsLongInSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

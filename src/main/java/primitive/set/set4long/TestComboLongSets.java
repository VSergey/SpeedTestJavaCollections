package primitive.set.set4long;

import com.carrotsearch.hppc.procedures.LongProcedure;
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
public class TestComboLongSets {
    @Param({"30000"})
    private int size;

    @Benchmark
    public long test_Oracle_HashSet() {
        Set<Long> set = new HashSet<>();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.forEach(i -> sum[0]+=i);
        return sum[0]-count;
    }

    @Benchmark
    public long test_Oracle_LinkedHashSet() {
        Set<Long> set = new LinkedHashSet<>();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.forEach(i -> sum[0]+=i);
        return sum[0]-count;
    }

    @Benchmark
    public long test_Oracle_TreeSet() {
        Set<Long> set = new TreeSet<>();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.forEach(i -> sum[0]+=i);
        return sum[0]-count;
    }

    @Benchmark
    public long test_HPPC_LongHashSet() {
        com.carrotsearch.hppc.LongHashSet set = new com.carrotsearch.hppc.LongHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        return set.forEach(new LongProcedure() {
            long sum;

            public void apply(long value) {
                sum+=value;
            }
        }).sum-count;
    }

    @Benchmark
    public long test_HPPC_LongScatterSet() {
        com.carrotsearch.hppc.LongScatterSet set = new com.carrotsearch.hppc.LongScatterSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        return set.forEach(new LongProcedure() {
            long sum;

            public void apply(long value) {
                sum+=value;
            }
        }).sum-count;
    }

    @Benchmark
    public long test_Koloboke_MutableLongSetQ() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl().newMutableSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.removeLong(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0]-count;
    }

    @Benchmark
    public long test_Koloboke_MutableLongSetL() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl().newMutableSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.removeLong(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0]-count;
    }

    @Benchmark
    public long test_Koloboke_UpdatableLongSetL() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl().newUpdatableSet();
        for(int i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.removeLong(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0]-count;
    }

    @Benchmark
    public long test_Koloboke_UpdatableLongSetQ() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl().newUpdatableSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.removeLong(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.cursor().forEachForward(value -> sum[0]+=value);
        return sum[0]-count;
    }

    @Benchmark
    public long test_Trove_TLongHashSet() {
        TLongHashSet set = new TLongHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.forEach(value -> {
            sum[0]+=value;
            return true;
        });
        return sum[0]-count;
    }

    @Benchmark
    public long test_Eclipse_LongHashSet() {
        org.eclipse.collections.impl.set.mutable.primitive.LongHashSet set = new org.eclipse.collections.impl.set.mutable.primitive.LongHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        final long sum[] = new long[1];
        set.forEach(i -> sum[0]+=i);
        return sum[0]-count;
    }

    @Benchmark
    public long test_Fastutil_LongOpenHashSet() {
        LongOpenHashSet set = new LongOpenHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        long sum = 0;
        for(LongIterator it = set.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum-count;
    }

    @Benchmark
    public long test_Fastutil_LongOpenHashBigSet() {
        LongOpenHashBigSet set = new LongOpenHashBigSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        long sum = 0;
        for(LongIterator it = set.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum-count;
    }

    @Benchmark
    public long test_Fastutil_LongAVLTreeSet() {
        LongAVLTreeSet set = new LongAVLTreeSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        System.err.println("LongAVLTreeSet "+set.size());
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        System.err.println("LongAVLTreeSet "+set.size());
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        System.err.println("LongAVLTreeSet "+set.size());
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        System.err.println("count = "+count);
        long sum = 0;
        for(LongIterator it = set.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        System.err.println("sum = "+(sum-count));
        return sum-count;
    }

    @Benchmark
    public long test_Fastutil_LongLinkedOpenHashSet() {
        LongLinkedOpenHashSet set = new LongLinkedOpenHashSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        long sum = 0;
        for(LongIterator it = set.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum-count;
    }

    @Benchmark
    public long test_Fastutil_LongRBTreeSet() {
        LongRBTreeSet set = new LongRBTreeSet();
        for(long i = 0; i < size; i+=2) {
            set.add(i);
        }
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(i);
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(i)) count++;
        }
        long sum = 0;
        for(LongIterator it = set.iterator(); it.hasNext(); ) {
            sum += it.nextLong();
        }
        return sum-count;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestComboLongSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

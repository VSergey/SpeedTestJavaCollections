package primitive.map.int2int;

import com.carrotsearch.hppc.IntIntScatterMap;
import com.carrotsearch.hppc.predicates.IntIntPredicate;
import com.koloboke.collect.map.hash.HashIntIntMap;
import com.koloboke.collect.map.hash.HashIntIntMaps;
import com.koloboke.function.IntIntConsumer;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import it.unimi.dsi.fastutil.ints.*;
import org.eclipse.collections.api.block.procedure.primitive.IntIntProcedure;
import org.eclipse.collections.api.block.procedure.primitive.IntObjectProcedure;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
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
public class TestComboIntMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public long test_Oracle_HashMap() {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((key,value) -> sum[0]+= key + value);
        return sum[0]-s;
    }

    @Benchmark
    public long test_Oracle_TreeMap() {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((key,value) -> sum[0]+= key + value);
        return sum[0]-s;
    }

    @Benchmark
    public long test_Oracle_LinkedHashMap() {
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((key,value) -> sum[0]+= key + value);
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_IntOpenHashMap() {
        Int2IntOpenHashMap map = new Int2IntOpenHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2IntEntrySet().forEach(entry -> sum[0]+= entry.getIntKey()+entry.getIntValue());
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_IntLinkedOpenHashMap() {
        Int2IntLinkedOpenHashMap map = new Int2IntLinkedOpenHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2IntEntrySet().forEach(entry -> sum[0]+= entry.getIntKey()+entry.getIntValue());
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_Int2IntAVLTreeMap() {
        Int2IntAVLTreeMap map = new Int2IntAVLTreeMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2IntEntrySet().forEach(entry -> sum[0]+= entry.getIntKey()+entry.getIntValue());
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_Int2IntRBTreeMap() {
        Int2IntRBTreeMap map = new Int2IntRBTreeMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2IntEntrySet().forEach(entry -> sum[0]+= entry.getIntKey()+entry.getIntValue());
        return sum[0]-s;
    }

    @Benchmark
    public long test_Hppc_IntIntHashMap() {
        com.carrotsearch.hppc.IntIntHashMap map = new com.carrotsearch.hppc.IntIntHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        return map.forEach(new IntIntPredicate() {
            long sum;
            public boolean apply(int key, int value) {
                sum+=key+value;
                return true;
            }
        }).sum-s;
    }

    @Benchmark
    public long test_Hppc_IntIntScatterMap() {
        IntIntScatterMap map = new IntIntScatterMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        return map.forEach(new IntIntPredicate() {
            long sum;
            public boolean apply(int key, int value) {
                sum+=key+value;
                return true;
            }
        }).sum-s;
    }

    @Benchmark
    public long test_Koloboke_MutableHashIntIntMap() {
        HashIntIntMap map = HashIntIntMaps.getDefaultFactory().newMutableMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((IntIntConsumer) (key, value) -> sum[0]+= key + value);
        return sum[0]-s;
    }

    @Benchmark
    public long test_Trove_TIntIntHashMap() {
        TIntIntMap map = new TIntIntHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEachEntry((key, value) -> {
            sum[0] += key + value;
            return true;
        });
        return sum[0]-s;
    }

    @Benchmark
    public long test_Eclipse_IntIntHashMap() {
        IntIntHashMap map = new IntIntHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i);
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEachKeyValue((IntIntProcedure) (key, value) -> sum[0] += key + value);
        return sum[0]-s;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestComboIntMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

package primitive.map.int2object;

import com.carrotsearch.hppc.IntObjectScatterMap;
import com.carrotsearch.hppc.predicates.IntObjectPredicate;
import com.koloboke.collect.map.hash.HashIntObjMap;
import com.koloboke.collect.map.hash.HashIntObjMaps;
import com.koloboke.function.IntObjConsumer;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.procedure.TIntObjectProcedure;
import gnu.trove.procedure.TIntProcedure;
import it.unimi.dsi.fastutil.ints.*;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.IntObjectProcedure;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
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
public class TestComboIntObjectMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public long test_Oracle_HashMap() {
        Map<Integer,Object> map = new HashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((key,value) -> sum[0]+= key);
        return sum[0]-s;
    }

    @Benchmark
    public long test_Oracle_TreeMap() {
        Map<Integer,Object> map = new TreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((key,value) -> sum[0]+= key);
        return sum[0]-s;
    }

    @Benchmark
    public long test_Oracle_LinkedHashMap() {
        Map<Integer,Object> map = new LinkedHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((key,value) -> sum[0]+= key);
        return sum[0]-s;
    }

    @Benchmark
    public long test_Hppc_IntObjectHashMap() {
        com.carrotsearch.hppc.IntObjectHashMap<Object> map = new com.carrotsearch.hppc.IntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        return map.forEach(new IntObjectPredicate<Object>() {
            long sum;
            public boolean apply(int key, Object value) {
                sum+=key;
                return true;
            }
        }).sum-s;
    }

    @Benchmark
    public long test_Hppc_IntObjectScatterMap() {
        IntObjectScatterMap<Object> map = new IntObjectScatterMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        return map.forEach(new IntObjectPredicate<Object>() {
            long sum;
            public boolean apply(int key, Object value) {
                sum+=key;
                return true;
            }
        }).sum-s;
    }

    @Benchmark
    public long test_Trove_TIntObjectHashMap() {
        TIntObjectHashMap<Object> map = new TIntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEachEntry((key, value) -> {
            sum[0] += key;
            return true;
        });
        return sum[0] - s;
    }

    @Benchmark
    public long test_Eclipse_IntObjectHashMap() {
        IntObjectHashMap<Object> map = new IntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEachKeyValue((IntObjectProcedure<Object>) (key, value) -> sum[0] += key);
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectOpenHashMap() {
        Int2ObjectOpenHashMap<Object> map = new Int2ObjectOpenHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2ObjectEntrySet().forEach(entry -> sum[0]+= entry.getIntKey());
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectRBTreeMap() {
        Int2ObjectRBTreeMap<Object> map = new Int2ObjectRBTreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2ObjectEntrySet().forEach(entry -> sum[0]+= entry.getIntKey());
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectAVLTreeMap() {
        Int2ObjectAVLTreeMap<Object> map = new Int2ObjectAVLTreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2ObjectEntrySet().forEach(entry -> sum[0]+= entry.getIntKey());
        return sum[0]-s;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectLinkedOpenHashMap() {
        Int2ObjectLinkedOpenHashMap<Object> map = new Int2ObjectLinkedOpenHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.int2ObjectEntrySet().forEach(entry -> sum[0]+= entry.getIntKey());
        return sum[0]-s;
    }

    @Benchmark
    public long test_Koloboke_MutableHashIntObjMap() {
        HashIntObjMap<Object> map = HashIntObjMaps.getDefaultFactory().newMutableMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        long s = 0;
        for(int i = 0; i < size; i+=3) {
            s += map.get(i).hashCode();
        }
        for(int i = 0; i < size; i+=3) {
            map.remove(i);
        }
        final long sum[] = new long[1];
        map.forEach((IntObjConsumer<Object>) (key,value) -> sum[0]+= key);
        return sum[0]-s;
    }

    @TearDown(Level.Iteration)
    public void clear()  {
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestComboIntObjectMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

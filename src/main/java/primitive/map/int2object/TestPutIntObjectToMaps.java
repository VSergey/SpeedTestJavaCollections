package primitive.map.int2object;

import com.carrotsearch.hppc.IntObjectScatterMap;
import com.koloboke.collect.map.hash.HashIntObjMap;
import com.koloboke.collect.map.hash.HashIntObjMaps;
import gnu.trove.map.hash.TIntObjectHashMap;
import it.unimi.dsi.fastutil.ints.*;
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
public class TestPutIntObjectToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Integer,Object> map = new HashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Integer,Object> map = new TreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Integer,Object> map = new LinkedHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Hppc_IntObjectHashMap() {
        com.carrotsearch.hppc.IntObjectHashMap<Object> map = new com.carrotsearch.hppc.IntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Hppc_IntObjectScatterMap() {
        IntObjectScatterMap<Object> map = new IntObjectScatterMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Trove_TIntObjectHashMap() {
        TIntObjectHashMap<Object> map = new TIntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Eclipse_IntObjectHashMap() {
        IntObjectHashMap<Object> map = new IntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectOpenHashMap() {
        Int2ObjectOpenHashMap<Object> map = new Int2ObjectOpenHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectRBTreeMap() {
        Int2ObjectRBTreeMap<Object> map = new Int2ObjectRBTreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectAVLTreeMap() {
        Int2ObjectAVLTreeMap<Object> map = new Int2ObjectAVLTreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectLinkedOpenHashMap() {
        Int2ObjectLinkedOpenHashMap<Object> map = new Int2ObjectLinkedOpenHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Koloboke_MutableHashIntObjMap() {
        HashIntObjMap<Object> map = HashIntObjMaps.getDefaultFactory().newMutableMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @Benchmark
    public void test_Koloboke_UpdatableHashIntObjMap() {
        HashIntObjMap<Object> map = HashIntObjMaps.getDefaultFactory().newUpdatableMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,new Object());
        }
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
    }

    @TearDown(Level.Iteration)
    public void clear()  {
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestPutIntObjectToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

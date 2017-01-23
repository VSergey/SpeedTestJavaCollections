package primitive.map.int2int;

import com.carrotsearch.hppc.IntIntScatterMap;
import com.koloboke.collect.map.hash.HashIntIntMap;
import com.koloboke.collect.map.hash.HashIntIntMaps;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import it.unimi.dsi.fastutil.ints.*;
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
public class TestPutIntIntToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_IntOpenHashMap() {
        Int2IntOpenHashMap map = new Int2IntOpenHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_IntLinkedOpenHashMap() {
        Int2IntLinkedOpenHashMap map = new Int2IntLinkedOpenHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2IntAVLTreeMap() {
        Int2IntAVLTreeMap map = new Int2IntAVLTreeMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2IntRBTreeMap() {
        Int2IntRBTreeMap map = new Int2IntRBTreeMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Hppc_IntIntHashMap() {
        com.carrotsearch.hppc.IntIntHashMap map = new com.carrotsearch.hppc.IntIntHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Hppc_IntIntScatterMap() {
        IntIntScatterMap map = new IntIntScatterMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Koloboke_HashIntIntMap() {
        HashIntIntMap map = HashIntIntMaps.getDefaultFactory().newMutableMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Trove_TIntIntHashMap() {
        TIntIntMap map = new TIntIntHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Eclipse_IntIntHashMap() {
        IntIntHashMap map = new IntIntHashMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(int i = 0; i < size; i++) {
            map.put(i,i);
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
                .include(TestPutIntIntToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

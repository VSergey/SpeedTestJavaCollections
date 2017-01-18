package primitive.map.int2int;

import com.carrotsearch.hppc.IntIntScatterMap;
import com.koloboke.collect.map.hash.HashIntIntMap;
import com.koloboke.collect.map.hash.HashIntIntMaps;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
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
public class TestGetIntFromMap {
    @Param({"30000"})
    private int size;

    private Map<Integer,Integer> map1;
    private Map<Integer,Integer> map2;
    private Map<Integer,Integer> map3;
    private Int2IntOpenHashMap map4;
    private Int2IntLinkedOpenHashMap map5;
    private com.carrotsearch.hppc.IntIntHashMap map6;
    private IntIntScatterMap map7;
    private HashIntIntMap map8;
    private TIntIntMap map9;
    private IntIntHashMap map10;

    @Benchmark
    public void test_Oracle_HashMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map1.get(i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map2.get(i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map3.get(i);
        }
    }

    @Benchmark
    public void test_FastUtil_IntOpenHashMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map4.get(i);
        }
    }

    @Benchmark
    public void test_FastUtil_IntLinkedOpenHashMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map5.get(i);
        }
    }

    @Benchmark
    public void test_Hppc_IntIntHashMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map6.get(i);
        }
    }

    @Benchmark
    public void test_Hppc_IntIntScatterMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map7.get(i);
        }
    }

    @Benchmark
    public void test_Koloboke_HashIntIntMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map8.get(i);
        }
    }

    @Benchmark
    public void test_Trove_TIntIntHashMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map9.get(i);
        }
    }

    @Benchmark
    public void test_Eclipse_IntIntHashMap() {
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            count += map10.get(i);
        }
    }

    @Setup(Level.Trial)
    public void setup() {
        map1 = new HashMap<>(size);
        map2 = new TreeMap<>();
        map3 = new LinkedHashMap<>(size);
        map4 = new Int2IntOpenHashMap(size);
        map5 = new Int2IntLinkedOpenHashMap(size);
        map6 = new com.carrotsearch.hppc.IntIntHashMap(size);
        map7 = new IntIntScatterMap(size);
        map8 = HashIntIntMaps.getDefaultFactory().newMutableMap(size);
        map9 = new TIntIntHashMap(size);
        map10 = new IntIntHashMap(size);

        for(int i = 0; i < size; i++) {
            map1.put(i,i);
            map2.put(i,i);
            map3.put(i,i);
            map4.put(i,i);
            map5.put(i,i);
            map6.put(i,i);
            map7.put(i,i);
            map8.put(i,i);
            map9.put(i,i);
            map10.put(i,i);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestGetIntFromMap.class.getSimpleName())
                .param("size","30000","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

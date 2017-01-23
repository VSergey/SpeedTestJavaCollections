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
public class TestRemoveIntFromMap {
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
    private Int2IntAVLTreeMap map11;
    private Int2IntRBTreeMap map12;

    @Benchmark
    public void test_Oracle_HashMap() {
        for(int i = 0; i < size; i+=3) {
            map1.remove(i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        for(int i = 0; i < size; i+=3) {
            map2.remove(i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedMap() {
        for(int i = 0; i < size; i+=3) {
            map3.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_IntOpenHashMap() {
        for(int i = 0; i < size; i+=3) {
            map4.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_IntLinkedOpenHashMap() {
        for(int i = 0; i < size; i+=3) {
            map5.remove(i);
        }
    }

    @Benchmark
    public void test_Hppc_IntIntHashMap() {
        for(int i = 0; i < size; i+=3) {
            map6.remove(i);
        }
    }

    @Benchmark
    public void test_Hppc_IntIntScatterMap() {
        for(int i = 0; i < size; i+=3) {
            map7.remove(i);
        }
    }

    @Benchmark
    public void test_Koloboke_HashIntIntMap() {
        for(int i = 0; i < size; i+=3) {
            map8.remove(i);
        }
    }

    @Benchmark
    public void test_Trove_TIntIntHashMap() {
        for(int i = 0; i < size; i+=3) {
            map9.remove(i);
        }
    }

    @Benchmark
    public void test_Eclipse_IntIntHashMap() {
        for(int i = 0; i < size; i+=3) {
            map10.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2IntAVLTreeMap() {
        for(int i = 0; i < size; i+=3) {
            map11.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2IntRBTreeMap() {
        for(int i = 0; i < size; i+=3) {
            map12.remove(i);
        }
    }

    @Setup(Level.Iteration)
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
        map11 = new Int2IntAVLTreeMap();
        map12 = new Int2IntRBTreeMap();

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
            map11.put(i,i);
            map12.put(i,i);
        }
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestRemoveIntFromMap.class.getSimpleName())
                .param("size","50000","100000","300000","500000","750000","1000000")
                .build();

        new Runner(opt).run();
    }
}

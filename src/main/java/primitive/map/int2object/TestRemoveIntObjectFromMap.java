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
public class TestRemoveIntObjectFromMap {
    @Param({"30000"})
    private int size;

    private Map<Integer,Object> map1;
    private Map<Integer,Object> map2;
    private Map<Integer,Object> map3;
    private com.carrotsearch.hppc.IntObjectHashMap<Object> map4;
    private IntObjectScatterMap<Object> map5;
    private TIntObjectHashMap<Object> map6;
    private IntObjectHashMap<Object> map7;
    private Int2ObjectOpenHashMap<Object> map8;
    private Int2ObjectRBTreeMap<Object> map9;
    private Int2ObjectAVLTreeMap<Object> map10;
    private Int2ObjectLinkedOpenHashMap<Object> map11;
    private HashIntObjMap<Object> map12;

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
    public void test_Oracle_LinkedHashMap() {
        for(int i = 0; i < size; i+=3) {
            map3.remove(i);
        }
    }

    @Benchmark
    public void test_Hppc_IntObjectHashMap() {
        for(int i = 0; i < size; i+=3) {
            map4.remove(i);
        }
    }

    @Benchmark
    public void test_Hppc_IntObjectScatterMap() {
        for(int i = 0; i < size; i+=3) {
            map5.remove(i);
        }
    }

    @Benchmark
    public void test_Trove_TIntObjectHashMap() {
        for(int i = 0; i < size; i+=3) {
            map6.remove(i);
        }
    }

    @Benchmark
    public void test_Eclipse_IntObjectHashMap() {
        for(int i = 0; i < size; i+=3) {
            map7.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectOpenHashMap() {
        for(int i = 0; i < size; i+=3) {
            map8.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectRBTreeMap() {
        for(int i = 0; i < size; i+=3) {
            map9.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectAVLTreeMap() {
        for(int i = 0; i < size; i+=3) {
            map10.remove(i);
        }
    }

    @Benchmark
    public void test_FastUtil_Int2ObjectLinkedOpenHashMap() {
        for(int i = 0; i < size; i+=3) {
            map11.remove(i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableHashIntObjMap() {
        for(int i = 0; i < size; i+=3) {
            map12.remove(i);
        }
    }

    @Setup(Level.Iteration)
    public void setup() {
        map1 = new HashMap<>();
        map2 = new TreeMap<>();
        map3 = new LinkedHashMap<>();
        map4 = new com.carrotsearch.hppc.IntObjectHashMap<>();
        map5 = new IntObjectScatterMap<>();
        map6 = new TIntObjectHashMap<>();
        map7 = new IntObjectHashMap<>();
        map8 = new Int2ObjectOpenHashMap<>();
        map9 = new Int2ObjectRBTreeMap<>();
        map10 = new Int2ObjectAVLTreeMap<>();
        map11 = new Int2ObjectLinkedOpenHashMap<>();
        map12 = HashIntObjMaps.getDefaultFactory().newMutableMap();

        for(int i = 0; i < size; i++) {
            Object o = new Object();
            map1.put(i,o);
            map2.put(i,o);
            map3.put(i,o);
            map4.put(i,o);
            map5.put(i,o);
            map6.put(i,o);
            map7.put(i,o);
            map8.put(i,o);
            map9.put(i,o);
            map10.put(i,o);
            map11.put(i,o);
            map12.put(i,o);
        }
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestRemoveIntObjectFromMap.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

package primitive.map.int2object;

import com.carrotsearch.hppc.IntObjectScatterMap;
import com.koloboke.collect.map.hash.*;
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
public class TestGetIntObjectFromMap {
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
    private HashIntObjMap<Object> map13;

    @Benchmark
    public long test_Oracle_HashMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map1.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Oracle_TreeMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map2.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Oracle_LinkedHashMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map3.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Hppc_IntObjectHashMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map4.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Hppc_IntObjectScatterMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map5.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Trove_TIntObjectHashMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map6.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Eclipse_IntObjectHashMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map7.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectOpenHashMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map8.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectRBTreeMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map9.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectAVLTreeMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map10.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_Int2ObjectLinkedOpenHashMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map11.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Koloboke_MutableHashIntObjMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map12.get(i).hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Koloboke_UpdatableHashIntObjMap() {
        long sum = 0;
        for(int i = 0; i < size; i+=3) {
            sum += map13.get(i).hashCode();
        }
        return sum;
    }

    @Setup(Level.Trial)
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
        map13 = HashIntObjMaps.getDefaultFactory().newUpdatableMap();

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
            map13.put(i,o);
        }
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestGetIntObjectFromMap.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

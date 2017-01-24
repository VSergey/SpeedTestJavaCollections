package objects.map;

import com.carrotsearch.hppc.*;
import com.google.common.collect.HashBiMap;
import com.koloboke.collect.map.hash.*;
import gnu.trove.map.hash.THashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.apache.commons.collections4.map.*;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
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
public class TestAddToMap {
    @Param({"30000"})
    private int size;

    private void fillMap(Map<String, String> map) {
        for(int i = 0; i < size; i+=2) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
        for(int i = 0; i < size; i++) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
    }

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<String,String> map = new HashMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<String,String> map = new TreeMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Oracle_LinkedMap() {
        Map<String,String> map = new LinkedHashMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Apache_HashedMap() {
        Map<String,String> map = new HashedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Apache_LinkedMap() {
        Map<String,String> map = new LinkedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Apache_PatriciaTrie() {
        Map<String,String> map = new PatriciaTrie<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Eclipse_UnifiedMap() {
        Map<String,String> map = new UnifiedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Eclipse_SortedTreeMap() {
        Map<String,String> map = new TreeSortedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Google_HashBiMap() {
        Map<String,String> map = HashBiMap.create();
        fillMap(map);
    }

    @Benchmark
    public void test_Koloboke_MutableHashObjObjMap() {
        Map<String,String> map = HashObjObjMaps.getDefaultFactory().newMutableMap();
        fillMap(map);
    }

    @Benchmark
    public void test_Koloboke_UpdatableHashObjObjMap() {
        Map<String,String> map = HashObjObjMaps.getDefaultFactory().newUpdatableMap();
        fillMap(map);
    }

    @Benchmark
    public void test_Hppc_ObjectHashMap() {
        ObjectObjectMap<String,String> map = new ObjectObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
        for(int i = 0; i < size; i++) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectScatterMap() {
        ObjectObjectMap<String,String> map = new ObjectObjectScatterMap<>();
        for(int i = 0; i < size; i+=2) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
        for(int i = 0; i < size; i++) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
    }

    @Benchmark
    public void test_FastUtil_Object2ObjectOpenHashMap() {
        Map<String,String> map = new Object2ObjectOpenHashMap<>();
        fillMap(map);
    }

    @Benchmark
    public void test_Trove_THashMap() {
        Map<String,String> map = new THashMap<>();
        fillMap(map);
    }

    @TearDown(Level.Iteration)
    public void clear()  {
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToMap.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

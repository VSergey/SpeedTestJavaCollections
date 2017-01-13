package objects.map;

import com.google.common.collect.HashBiMap;
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
    private static final int SIZE = 30000;

    private void fillMap(Map<String, String> map) {
        for(int i = 0; i < SIZE; i+=2) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
        for(int i = 0; i < SIZE; i++) {
            String key = Integer.toString(i);
            map.put(key, key);
        }
    }

    @Benchmark
    public void testOracleHashMap() {
        Map<String,String> map = new HashMap<>();
        fillMap(map);
    }

    @Benchmark
    public void testOracleTreeMap() {
        Map<String,String> map = new TreeMap<>();
        fillMap(map);
    }

    @Benchmark
    public void testOracleLinkedMap() {
        Map<String,String> map = new LinkedHashMap<>();
        fillMap(map);
    }

    @Benchmark
    public void testApacheHashedMap() {
        Map<String,String> map = new HashedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void testApacheLinkedMap() {
        Map<String,String> map = new LinkedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void testApachePatriciaTrie() {
        Map<String,String> map = new PatriciaTrie<>();
        fillMap(map);
    }

    @Benchmark
    public void testEclipseUnifiedMap() {
        Map<String,String> map = new UnifiedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void testEclipseSortedTreeMap() {
        Map<String,String> map = new TreeSortedMap<>();
        fillMap(map);
    }

    @Benchmark
    public void testGoogleHashBiMap() {
        Map<String,String> map = HashBiMap.create();
        fillMap(map);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToMap.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}

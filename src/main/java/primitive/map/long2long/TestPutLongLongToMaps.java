package primitive.map.long2long;

import com.carrotsearch.hppc.LongLongScatterMap;
import com.koloboke.collect.map.hash.*;
import gnu.trove.map.TLongLongMap;
import gnu.trove.map.hash.TLongLongHashMap;
import it.unimi.dsi.fastutil.longs.*;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongLongHashMap;
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
public class TestPutLongLongToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Long,Long> map = new HashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Long,Long> map = new TreeMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Long,Long> map = new LinkedHashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_LongOpenHashMap() {
        Long2LongOpenHashMap map = new Long2LongOpenHashMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_LongLinkedOpenHashMap() {
        Long2LongLinkedOpenHashMap map = new Long2LongLinkedOpenHashMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_LongAVLTreeMap() {
        Long2LongAVLTreeMap map = new Long2LongAVLTreeMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_FastUtil_LongRBTreeMap() {
        Long2LongRBTreeMap map = new Long2LongRBTreeMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Hppc_LongLongHashMap() {
        com.carrotsearch.hppc.LongLongHashMap map = new com.carrotsearch.hppc.LongLongHashMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Hppc_LongLongScatterMap() {
        LongLongScatterMap map = new LongLongScatterMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Koloboke_MutableHashLongLongMap() {
        HashLongLongMap map = HashLongLongMaps.getDefaultFactory().newMutableMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }
    @Benchmark
    public void test_Koloboke_UpdatableHashLongLongMap() {
        HashLongLongMap map = HashLongLongMaps.getDefaultFactory().newUpdatableMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Trove_TLongLongHashMap() {
        TLongLongMap map = new TLongLongHashMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void test_Eclipse_LongLongHashMap() {
        LongLongHashMap map = new LongLongHashMap();
        for(long i = 0; i < size; i+=2) {
            map.put(i,i);
        }
        for(long i = 0; i < size; i++) {
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
                .include(TestPutLongLongToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

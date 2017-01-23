package primitive.map.string2int;

import com.carrotsearch.hppc.ObjectIntScatterMap;
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
public class TestPutStringIntToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(int i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<String,Integer> map = new TreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(int i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<String,Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(int i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectIntHashMap() {
        com.carrotsearch.hppc.ObjectIntHashMap<String> map = new com.carrotsearch.hppc.ObjectIntHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(int i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectIntScatterMap() {
        ObjectIntScatterMap<String> map = new ObjectIntScatterMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(int i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
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
                .include(TestPutStringIntToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

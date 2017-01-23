package primitive.map.string2long;

import com.carrotsearch.hppc.ObjectDoubleScatterMap;
import com.carrotsearch.hppc.ObjectLongScatterMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import primitive.map.string2double.TestPutStringDoubleToMaps;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TestPutStringLongToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<String,Long> map = new HashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(long i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<String,Long> map = new TreeMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(long i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<String,Long> map = new LinkedHashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(long i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectLongHashMap() {
        com.carrotsearch.hppc.ObjectLongHashMap<String> map = new com.carrotsearch.hppc.ObjectLongHashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(long i = 0; i < size; i++) {
            map.put(String.valueOf(i),i);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectLongScatterMap() {
        ObjectLongScatterMap<String> map = new ObjectLongScatterMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(String.valueOf(i),i);
        }
        for(long i = 0; i < size; i++) {
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
                .include(TestPutStringLongToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

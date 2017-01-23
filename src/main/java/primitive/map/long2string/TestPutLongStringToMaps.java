package primitive.map.long2string;

import com.carrotsearch.hppc.LongObjectScatterMap;
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
public class TestPutLongStringToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Long,String> map = new HashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(long i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Long,String> map = new TreeMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(long i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Long,String> map = new LinkedHashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(long i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Hppc_LongObjectHashMap() {
        com.carrotsearch.hppc.LongObjectHashMap<String> map = new com.carrotsearch.hppc.LongObjectHashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(long i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Hppc_LongObjectScatterMap() {
        LongObjectScatterMap<String> map = new LongObjectScatterMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(long i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
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
                .include(TestPutLongStringToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

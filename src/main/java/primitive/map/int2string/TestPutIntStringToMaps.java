package primitive.map.int2string;

import com.carrotsearch.hppc.IntObjectScatterMap;
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
public class TestPutIntStringToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Integer,String> map = new HashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Integer,String> map = new TreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Integer,String> map = new LinkedHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Hppc_LongLongHashMap() {
        com.carrotsearch.hppc.IntObjectHashMap<String> map = new com.carrotsearch.hppc.IntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
    }

    @Benchmark
    public void test_Hppc_LongLongScatterMap() {
        IntObjectScatterMap<String> map = new IntObjectScatterMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
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
                .include(TestPutIntStringToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

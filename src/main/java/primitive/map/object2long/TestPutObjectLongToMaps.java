package primitive.map.object2long;

import com.carrotsearch.hppc.ObjectLongScatterMap;
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
public class TestPutObjectLongToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Integer,Long> map = new HashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put((int)i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put((int)i,i);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Integer,Long> map = new TreeMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put((int)i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put((int)i,i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Integer,Long> map = new LinkedHashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put((int)i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put((int)i,i);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectLongHashMap() {
        com.carrotsearch.hppc.ObjectLongHashMap<Integer> map = new com.carrotsearch.hppc.ObjectLongHashMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put((int)i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put((int)i,i);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectLongScatterMap() {
        ObjectLongScatterMap<Integer> map = new ObjectLongScatterMap<>();
        for(long i = 0; i < size; i+=2) {
            map.put((int)i,i);
        }
        for(long i = 0; i < size; i++) {
            map.put((int)i,i);
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
                .include(TestPutObjectLongToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

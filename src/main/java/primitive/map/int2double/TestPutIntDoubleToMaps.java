package primitive.map.int2double;

import com.carrotsearch.hppc.IntDoubleScatterMap;
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
public class TestPutIntDoubleToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Integer,Double> map = new HashMap<>();
        for(int i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(int i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Integer,Double> map = new TreeMap<>();
        for(int i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(int i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Integer,Double> map = new LinkedHashMap<>();
        for(int i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(int i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Hppc_IntDoubleHashMap() {
        com.carrotsearch.hppc.IntDoubleHashMap map = new com.carrotsearch.hppc.IntDoubleHashMap();
        for(int i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(int i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Hppc_IntDoubleScatterMap() {
        IntDoubleScatterMap map = new IntDoubleScatterMap();
        for(int i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(int i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
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
                .include(TestPutIntDoubleToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

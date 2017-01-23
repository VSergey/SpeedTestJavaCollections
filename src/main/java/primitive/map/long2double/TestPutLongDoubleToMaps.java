package primitive.map.long2double;

import com.carrotsearch.hppc.IntDoubleScatterMap;
import com.carrotsearch.hppc.LongDoubleScatterMap;
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
public class TestPutLongDoubleToMaps {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_HashMap() {
        Map<Long,Double> map = new HashMap<>();
        for(long i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(long i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Oracle_TreeMap() {
        Map<Long,Double> map = new TreeMap<>();
        for(long i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(long i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedHashMap() {
        Map<Long,Double> map = new LinkedHashMap<>();
        for(long i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(long i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Hppc_LongDoubleHashMap() {
        com.carrotsearch.hppc.LongDoubleHashMap map = new com.carrotsearch.hppc.LongDoubleHashMap();
        for(long i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(long i = 0; i < size; i++) {
            double v = i/3.333;
            map.put(i,v);
        }
    }

    @Benchmark
    public void test_Hppc_LongDoubleScatterMap() {
        LongDoubleScatterMap map = new LongDoubleScatterMap();
        for(long i = 0; i < size; i+=2) {
            double v = i/3.333;
            map.put(i,v);
        }
        for(long i = 0; i < size; i++) {
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
                .include(TestPutLongDoubleToMaps.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

package primitive.list;

import com.carrotsearch.hppc.IntArrayList;
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
public class TestAddToIntList {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_ArrayList() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedList() {
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public void test_Hppc_IntArrayList() {
        IntArrayList list = new IntArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToIntList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

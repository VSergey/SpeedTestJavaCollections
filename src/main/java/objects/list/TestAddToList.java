package objects.list;

import com.carrotsearch.hppc.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;
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
public class TestAddToList {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_ArrayList() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Oracle_LinkedList() {
        List<String> list = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_Hppc_ObjectArrayList() {
        ObjectArrayList list = new ObjectArrayList();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_FastUtil_ObjectBigArrayBigList() {
        ObjectBigArrayBigList list = new ObjectBigArrayBigList();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
    }

    @Benchmark
    public void test_FastUtil_ObjectArrayList() {
        it.unimi.dsi.fastutil.objects.ObjectArrayList list = new it.unimi.dsi.fastutil.objects.ObjectArrayList();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

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
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TestAddToList {
    @Param({"30000"})
    private int size;
    private String[] objects;

    @Benchmark
    public void test_Oracle_ArrayList() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(objects[i]);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedList() {
        List<String> list = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            list.add(objects[i]);
        }
    }

    @Benchmark
    public void test_Hppc_ObjectArrayList() {
        ObjectArrayList<String> list = new ObjectArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(objects[i]);
        }
    }

    @Benchmark
    public void test_FastUtil_ObjectBigArrayBigList() {
        ObjectBigArrayBigList<String> list = new ObjectBigArrayBigList<>();
        for(int i = 0; i < size; i++) {
            list.add(objects[i]);
        }
    }

    @Benchmark
    public void test_FastUtil_ObjectArrayList() {
        it.unimi.dsi.fastutil.objects.ObjectArrayList<String> list = new it.unimi.dsi.fastutil.objects.ObjectArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(objects[i]);
        }
    }

    @Setup(Level.Trial)
    public void setup() {
        objects = new String[size];
        for(int i = 0; i < size; i++) {
            objects[i] = Integer.toString(i);
        }
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

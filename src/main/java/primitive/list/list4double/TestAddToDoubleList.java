package primitive.list.list4double;

import com.carrotsearch.hppc.DoubleArrayList;
import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.list.linked.TDoubleLinkedList;
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
public class TestAddToDoubleList {
    @Param({"30000"})
    private int size;

    @Benchmark
    public void test_Oracle_ArrayList() {
        List<Double> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
    }

    @Benchmark
    public void test_Oracle_LinkedList() {
        List<Double> list = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
    }

    @Benchmark
    public void test_Hppc_DoubleArrayList() {
        DoubleArrayList list = new DoubleArrayList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
    }

    @Benchmark
    public void test_Trove_TDoubleArrayList() {
        TDoubleArrayList list = new TDoubleArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public void test_Trove_TDoubleLinkedList() {
        TDoubleLinkedList list = new TDoubleLinkedList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public void test_Eclipse_DoubleArrayList() {
        org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList list = new org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
    }

    @Benchmark
    public void test_FastUtil_DoubleArrayList() {
        it.unimi.dsi.fastutil.doubles.DoubleArrayList list = new it.unimi.dsi.fastutil.doubles.DoubleArrayList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
    }

    @Benchmark
    public void test_FastUtil_DoubleBigArrayBigList() {
        it.unimi.dsi.fastutil.doubles.DoubleBigList list = new it.unimi.dsi.fastutil.doubles.DoubleBigArrayBigList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddToDoubleList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

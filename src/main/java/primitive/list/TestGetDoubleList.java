package primitive.list;

import com.carrotsearch.hppc.DoubleArrayList;
import com.carrotsearch.hppc.cursors.DoubleCursor;
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
public class TestGetDoubleList {
    @Param({"30000"})
    private int size;
    private List<Double> list1;
    private List<Double> list2;
    private DoubleArrayList list3;

    @Benchmark
    public double test_Oracle_ArrayList() {
        double sum = 0;
        for(double v : list1) {
            sum += v;
        }
        return sum;
    }

    @Benchmark
    public double test_Oracle_LinkedList() {
        double sum = 0;
        for(double v : list2) {
            sum += v;
        }
        return sum;
    }

    @Benchmark
    public double test_Hppc_DoubleArrayList() {
        double sum = 0;
        for(DoubleCursor v : list3) {
            sum += v.value;
        }
        return sum;
    }

    @Setup(Level.Trial)
    public void setup() {
        list1 = new ArrayList<>(size);
        list2 = new LinkedList<>();
        list3 = new DoubleArrayList(size);

        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list1.add(v);
            list2.add(v);
            list3.add(v);
        }

        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestGetDoubleList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

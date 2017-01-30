package primitive.list;

import com.carrotsearch.hppc.DoubleArrayList;
import com.carrotsearch.hppc.cursors.DoubleCursor;
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
public class TestGetDoubleList {
    @Param({"30000"})
    private int size;
    private List<Double> list1;
    private List<Double> list2;
    private DoubleArrayList list3;
    private TDoubleArrayList list4;
//    private TDoubleLinkedList list5;
    private org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList list6;
    private it.unimi.dsi.fastutil.doubles.DoubleArrayList list7;
    private it.unimi.dsi.fastutil.doubles.DoubleBigList list8;

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

    @Benchmark
    public double test_Trove_DoubleArrayList() {
        double sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list4.get(i);
        }
        return sum;
    }

//    @Benchmark
//    public double test_Trove_DoubleLinkedList() {
//        double sum = 0;
//        for(int i = 0; i < size; i++) {
//            sum += list5.get(i);
//        }
//        return sum;
//    }

    @Benchmark
    public double test_Eclipse_DoubleArrayList() {
        double sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list6.get(i);
        }
        return sum;
    }

    @Benchmark
    public double test_FastUtil_DoubleArrayList() {
        double sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list7.getDouble(i);
        }
        return sum;
    }

    @Benchmark
    public double test_FastUtil_DoubleBigArrayBigList() {
        double sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list8.getDouble(i);
        }
        return sum;
    }

    @Setup(Level.Trial)
    public void setup() {
        list1 = new ArrayList<>(size);
        list2 = new LinkedList<>();
        list3 = new DoubleArrayList(size);
        list4 = new TDoubleArrayList(size);
//        list5 = new TDoubleLinkedList();
        list6 = new org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList(size);
        list7 = new it.unimi.dsi.fastutil.doubles.DoubleArrayList(size);
        list8 = new it.unimi.dsi.fastutil.doubles.DoubleBigArrayBigList(size);

        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list1.add(v);
            list2.add(v);
            list3.add(v);
            list4.add(v);
//            list5.add(v);
            list6.add(v);
            list7.add(v);
            list8.add(v);
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

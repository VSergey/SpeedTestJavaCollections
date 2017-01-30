package primitive.list;

import com.carrotsearch.hppc.IntArrayList;
import com.carrotsearch.hppc.cursors.IntCursor;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.linked.TIntLinkedList;
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
public class TestGetIntList {
    @Param({"30000"})
    private int size;
    private List<Integer> list1;
    private List<Integer> list2;
    private IntArrayList list3;
    private TIntArrayList list4;
//    private TIntLinkedList list5;
    private org.eclipse.collections.impl.list.mutable.primitive.IntArrayList list6;
    private it.unimi.dsi.fastutil.ints.IntArrayList list7;
    private it.unimi.dsi.fastutil.ints.IntBigArrayBigList list8;

    @Benchmark
    public long test_Oracle_ArrayList() {
        long sum = 0;
        for(double v : list1) {
            sum += v;
        }
        return sum;
    }

    @Benchmark
    public long test_Oracle_LinkedList() {
        long sum = 0;
        for(double v : list2) {
            sum += v;
        }
        return sum;
    }

    @Benchmark
    public long test_Hppc_IntArrayList() {
        long sum = 0;
        for(IntCursor v : list3) {
            sum += v.value;
        }
        return sum;
    }

    @Benchmark
    public long test_Trove_TIntArrayList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list4.get(i);
        }
        return sum;
    }

//    @Benchmark
//    public long test_Trove_TIntLinkedList() {
//        long sum = 0;
//        for(int i = 0; i < size; i++) {
//            sum += list5.get(i);
//        }
//        return sum;
//    }

    @Benchmark
    public long test_Eclipse_IntArrayList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list6.get(i);
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_IntArrayList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list7.getInt(i);
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_IntBigArrayBigList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list8.getInt(i);
        }
        return sum;
    }

    @Setup(Level.Trial)
    public void setup() {
        list1 = new ArrayList<>(size);
        list2 = new LinkedList<>();
        list3 = new IntArrayList(size);
        list4 = new TIntArrayList(size);
//        list5 = new TIntLinkedList();
        list6 = new org.eclipse.collections.impl.list.mutable.primitive.IntArrayList(size);
        list7 = new it.unimi.dsi.fastutil.ints.IntArrayList(size);
        list8 = new it.unimi.dsi.fastutil.ints.IntBigArrayBigList(size);

        for(int i = 0; i < size; i++) {
            list1.add(i);
            list2.add(i);
            list3.add(i);
            list4.add(i);
//            list5.add(i);
            list6.add(i);
            list7.add(i);
            list8.add(i);
        }

        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestGetIntList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }

}

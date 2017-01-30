package primitive.list;

import com.carrotsearch.hppc.LongArrayList;
import com.carrotsearch.hppc.cursors.LongCursor;
import gnu.trove.list.array.TLongArrayList;
import gnu.trove.list.linked.TLongLinkedList;
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
public class TestGetLongList {
    @Param({"30000"})
    private int size;
    private List<Long> list1;
    private List<Long> list2;
    private LongArrayList list3;
    private TLongArrayList list4;
//    private TLongLinkedList list5;
    private org.eclipse.collections.impl.list.mutable.primitive.LongArrayList list6;
    private it.unimi.dsi.fastutil.longs.LongArrayList list7;
    private it.unimi.dsi.fastutil.longs.LongBigList list8;

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
    public long test_Hppc_LongArrayList() {
        long sum = 0;
        for(LongCursor v : list3) {
            sum += v.value;
        }
        return sum;
    }

    @Benchmark
    public long test_Trove_TLongArrayList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list4.get(i);
        }
        return sum;
    }

//    @Benchmark
//    public long test_Trove_TLongLinkedList() {
//        long sum = 0;
//        for(int i = 0; i < size; i++) {
//            sum += list5.get(i);
//        }
//        return sum;
//    }

    @Benchmark
    public long test_Eclipse_LongArrayList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list6.get(i);
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_LongArrayList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list7.getLong(i);
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_LongBigArrayBigList() {
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list8.getLong(i);
        }
        return sum;
    }

    @Setup(Level.Trial)
    public void setup() {
        list1 = new ArrayList<>(size);
        list2 = new LinkedList<>();
        list3 = new LongArrayList(size);
        list4 = new TLongArrayList(size);
//        list5 = new TLongLinkedList();
        list6 = new org.eclipse.collections.impl.list.mutable.primitive.LongArrayList(size);
        list7 = new it.unimi.dsi.fastutil.longs.LongArrayList(size);
        list8 = new it.unimi.dsi.fastutil.longs.LongBigArrayBigList(size);

        for(long i = 0; i < size; i++) {
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
                .include(TestGetLongList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

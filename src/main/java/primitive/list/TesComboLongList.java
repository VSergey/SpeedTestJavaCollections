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
public class TesComboLongList {
    @Param({"30000"})
    private int size;

    @Benchmark
    public long test_Oracle_ArrayList() {
        List<Long> list = new ArrayList<>();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(long v : list) {
            sum += v;
        }
        return sum;
    }

    @Benchmark
    public long test_Oracle_LinkedList() {
        List<Long> list = new LinkedList<>();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(long v : list) {
            sum += v;
        }
        return sum;
    }

    @Benchmark
    public long test_Hppc_LongArrayList() {
        LongArrayList list = new LongArrayList();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(LongCursor v : list) {
            sum += v.value;
        }
        return sum;
    }

    @Benchmark
    public long test_Trove_TLongArrayList() {
        TLongArrayList list = new TLongArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    @Benchmark
    public long test_Trove_TLongLinkedList() {
        TLongLinkedList list = new TLongLinkedList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    @Benchmark
    public long test_Eclipse_LongArrayList() {
        org.eclipse.collections.impl.list.mutable.primitive.LongArrayList list = new org.eclipse.collections.impl.list.mutable.primitive.LongArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_LongArrayList() {
        it.unimi.dsi.fastutil.longs.LongArrayList list = new it.unimi.dsi.fastutil.longs.LongArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list.getLong(i);
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_LongBigArrayBigList() {
        it.unimi.dsi.fastutil.longs.LongBigList list = new it.unimi.dsi.fastutil.longs.LongBigArrayBigList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        long sum = 0;
        for(int i = 0; i < size; i++) {
            sum += list.getLong(i);
        }
        return sum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TesComboLongList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

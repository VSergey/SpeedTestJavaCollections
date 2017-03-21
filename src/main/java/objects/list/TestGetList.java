package objects.list;

import com.carrotsearch.hppc.ObjectArrayList;
import com.carrotsearch.hppc.cursors.ObjectCursor;
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
public class TestGetList {
    @Param({"30000"})
    private int size;
    private List<String> list1;
    private List<String> list2;
    private ObjectArrayList<String> list3;
    private ObjectBigArrayBigList<String> list4;
    private it.unimi.dsi.fastutil.objects.ObjectArrayList<String> list5;

    @Benchmark
    public long test_Oracle_ArrayList() {
        long sum = 0;
        for(String s : list1) {
            sum += s.hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Oracle_LinkedList() {
        long sum = 0;
        for(String s : list2) {
            sum += s.hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_Hppc_ObjectArrayList() {
        long sum = 0;
        for(ObjectCursor<String> c : list3) {
            sum += c.value.hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_ObjectBigArrayBigList() {
        long sum = 0;
        for(String s : list4) {
            sum += s.hashCode();
        }
        return sum;
    }

    @Benchmark
    public long test_FastUtil_ObjectArrayList() {
        long sum = 0;
        for(String s : list5) {
            sum += s.hashCode();
        }
        return sum;
    }

    @Setup(Level.Trial)
    public void setup() {
        list1 = new ArrayList<>(size);
        list2 = new LinkedList<>();
        list3 = new ObjectArrayList<>(size);
        list4 = new ObjectBigArrayBigList<>(size);
        list5 = new it.unimi.dsi.fastutil.objects.ObjectArrayList<>(size);

        for(int i = 0; i < size; i++) {
            String s = Integer.toString(i);
            list1.add(s);
            list2.add(s);
            list3.add(s);
            list4.add(s);
            list5.add(s);
        }
        System.gc();
        System.gc();
        System.gc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestGetList.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

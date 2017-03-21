package objects.set;

import com.carrotsearch.hppc.*;
import com.carrotsearch.hppc.procedures.ObjectProcedure;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TLinkedHashSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
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
public class TestComboSets {
    @Param({"30000"})
    private int size;

    @Benchmark
    public long test_Oracle_HashSet() {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> sum[0]+=s.hashCode());
        return sum[0]-count;
    }

    @Benchmark
    public long test_Oracle_TreeSet() {
        Set<String> set = new TreeSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> sum[0]+=s.hashCode());
        return sum[0]-count;
    }

    @Benchmark
    public long test_Oracle_LinkedSet() {
        Set<String> set = new LinkedHashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> sum[0]+=s.hashCode());
        return sum[0]-count;
    }

    @Benchmark
    public long test_Eclipse_UnifiedSet() {
        Set<String> set = new UnifiedSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> sum[0]+=s.hashCode());
        return sum[0]-count;
    }

    @Benchmark
    public long test_Eclipse_TreeSortedSet() {
        Set<String> set = new TreeSortedSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> sum[0]+=s.hashCode());
        return sum[0]-count;
    }

    @Benchmark
    public long test_Hppc_ObjectHashSet() {
        ObjectHashSet<String> set = new ObjectHashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum = set.forEach(new ObjectProcedure<String>() {
            long sum;

            public void apply(String value) {
                sum+=value.hashCode();
            }
        }).sum;
        return sum-count;
    }

    @Benchmark
    public long test_Hppc_ObjectScatterSet() {
        ObjectScatterSet<String> set = new ObjectScatterSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum = set.forEach(new ObjectProcedure<String>() {
            long sum;

            public void apply(String value) {
                sum+=value.hashCode();
            }
        }).sum;
        return sum-count;
    }

    @Benchmark
    public long test_FastUtil_ObjectOpenHashSet() {
        ObjectOpenHashSet<String> set = new ObjectOpenHashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> sum[0]+=s.hashCode());
        return sum[0]-count;
    }

    @Benchmark
    public long test_FastUtil_ObjectOpenHashBigSet() {
        ObjectOpenHashBigSet<String> set = new ObjectOpenHashBigSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> sum[0]+=s.hashCode());
        return sum[0]-count;
    }

    @Benchmark
    public long test_Trove_THashSet() {
        THashSet<String> set = new THashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> {
            sum[0]+=s.hashCode();
            return true;
        });
        return sum[0]-count;
    }

    @Benchmark
    public long test_Trove_TLinkedHashSet() {
        TLinkedHashSet<String> set = new TLinkedHashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        for(int i = 1; i < size; i+=3) {
            set.remove(Integer.toString(i));
        }
        int count = 0;
        for(int i = 0; i < size; i+=3) {
            if(set.contains(Integer.toString(i))) count++;
        }
        final long sum[] = new long[1];
        set.forEach(s -> {
            sum[0]+=s.hashCode();
            return true;
        });
        return sum[0]-count;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestComboSets.class.getSimpleName())
                .param("size","50000","100000","500000","1000000")
                .build();

        new Runner(opt).run();
    }
}

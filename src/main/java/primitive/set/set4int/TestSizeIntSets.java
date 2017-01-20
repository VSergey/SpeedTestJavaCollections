package primitive.set.set4int;

import com.koloboke.collect.impl.hash.*;
import gnu.trove.set.hash.TIntHashSet;
import it.unimi.dsi.fastutil.ints.*;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Sergey.Voronezhtsev on 19/1/2017.
 */
public class TestSizeIntSets {
    private int size;

    public TestSizeIntSets(int size) {
        this.size = size;
    }

    public Object get_Oracle_array() {
        int array[] = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public Object get_Oracle_HashSet() {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Oracle_LinkedHashSet() {
        Set<Integer> set = new LinkedHashSet<>();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Oracle_TreeSet() {
        Set<Integer> set = new TreeSet<>();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_HPPC_IntHashSet() {
        com.carrotsearch.hppc.IntHashSet set = new com.carrotsearch.hppc.IntHashSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_HPPC_IntScatterSet() {
        com.carrotsearch.hppc.IntHashSet set = new com.carrotsearch.hppc.IntScatterSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Eclipse_IntHashSet() {
        org.eclipse.collections.impl.set.mutable.primitive.IntHashSet set = new org.eclipse.collections.impl.set.mutable.primitive.IntHashSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_IntOpenHashSet() {
        IntOpenHashSet set = new IntOpenHashSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_IntOpenHashBigSet() {
        IntOpenHashBigSet set = new IntOpenHashBigSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_IntAVLTreeSet() {
        IntAVLTreeSet set = new IntAVLTreeSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_IntLinkedOpenHashSet() {
        IntLinkedOpenHashSet set = new IntLinkedOpenHashSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_IntRBTreeSet() {
        IntRBTreeSet set = new IntRBTreeSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_MutableLHashIntSet() {
        MutableLHashIntSetGO set = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_MutableQHashIntSet() {
        MutableQHashIntSetGO set = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newMutableSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_UpdatableLHashIntSet() {
        UpdatableLHashIntSetGO set = new com.koloboke.collect.impl.hash.LHashIntSetFactoryImpl().newUpdatableSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_UpdatableQHashIntSet() {
        UpdatableQHashIntSetGO set = new com.koloboke.collect.impl.hash.QHashIntSetFactoryImpl().newUpdatableSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Trove_TIntHashSet() {
        TIntHashSet set = new TIntHashSet();
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public void printSizeOfObjects(PrintWriter pw) {
        printSizeOfObject(get_Oracle_HashSet(), "Oracle_HashSet", pw);
        printSizeOfObject(get_Oracle_LinkedHashSet(), "Oracle_LinkedHashSet", pw);
        printSizeOfObject(get_Oracle_TreeSet(), "Oracle_TreeSet", pw);
        printSizeOfObject(get_HPPC_IntHashSet(), "HPPC_IntHashSet", pw);
        printSizeOfObject(get_HPPC_IntScatterSet(), "HPPC_IntScatterSet", pw);
        printSizeOfObject(get_Eclipse_IntHashSet(), "Eclipse_IntHashSet", pw);
        printSizeOfObject(get_Fastutil_IntOpenHashSet(), "Fastutil_IntOpenHashSet", pw);
        printSizeOfObject(get_Fastutil_IntOpenHashBigSet(), "Fastutil_IntOpenHashBigSet", pw);
        printSizeOfObject(get_Fastutil_IntAVLTreeSet(), "Fastutil_IntAVLTreeSet", pw);
        printSizeOfObject(get_Fastutil_IntLinkedOpenHashSet(), "Fastutil_IntLinkedOpenHashSet", pw);
        printSizeOfObject(get_Fastutil_IntRBTreeSet(), "Fastutil_IntRBTreeSet", pw);
        printSizeOfObject(get_Koloboke_MutableLHashIntSet(), "Koloboke_MutableLHashIntSet", pw);
        printSizeOfObject(get_Koloboke_MutableQHashIntSet(), "Koloboke_MutableQHashIntSet", pw);
        printSizeOfObject(get_Koloboke_UpdatableLHashIntSet(), "Koloboke_UpdatableLHashIntSet", pw);
        printSizeOfObject(get_Koloboke_UpdatableQHashIntSet(), "Koloboke_UpdatableQHashIntSet", pw);
        printSizeOfObject(get_Trove_TIntHashSet(), "Trove_TIntHashSet", pw);
        printSizeOfObject(get_Oracle_array(), "Oracle_array", pw);
    }

    private static void printSizeOfObject(Object obj, String name, PrintWriter pw) {
        GraphLayout graphLayout = GraphLayout.parseInstance(new Object[]{obj});
//        pw.println(graphLayout.toPrintable());
        pw.print(name);
        pw.print("\t\t");
        pw.print(" count = ");
        pw.print(graphLayout.totalCount());
        pw.print(" size = ");
        pw.println(graphLayout.totalSize());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        PrintWriter pw = new PrintWriter(System.out, true);
        int sizes[] = new int[] {50000, 100000, 500000, 1000000};
//        int sizes[] = new int[] {1000000, 5000000};

        for(int size : sizes) {
            TestSizeIntSets test = new TestSizeIntSets(size);
            pw.println("-----------------------------" + size + "--------------------------------");
            test.printSizeOfObjects(pw);
            System.gc();
        }
    }
}

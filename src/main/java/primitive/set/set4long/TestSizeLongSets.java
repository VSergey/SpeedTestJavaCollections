package primitive.set.set4long;

import gnu.trove.set.hash.TLongHashSet;
import it.unimi.dsi.fastutil.longs.*;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Sergey.Voronezhtsev on 20/1/2017.
 */
public class TestSizeLongSets {
    private int size;

    public TestSizeLongSets(int size) {
        this.size = size;
    }

    public Object get_Oracle_array() {
        long array[] = new long[size];
        for(int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public Object get_Oracle_HashSet() {
        Set<Long> set = new HashSet<>(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Oracle_LinkedHashSet() {
        Set<Long> set = new LinkedHashSet<>(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Oracle_TreeSet() {
        Set<Long> set = new TreeSet<>();
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_HPPC_LongHashSet() {
        com.carrotsearch.hppc.LongSet set = new com.carrotsearch.hppc.LongHashSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_HPPC_LongScatterSet() {
        com.carrotsearch.hppc.LongSet set = new com.carrotsearch.hppc.LongScatterSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_MutableLongSetQ() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl().newMutableSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_MutableLongSetL() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl().newMutableSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_UpdatableLongSetQ() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.QHashLongSetFactoryImpl().newUpdatableSet(size);
        for(int i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Koloboke_UpdatableLongSetL() {
        com.koloboke.collect.set.LongSet set = new com.koloboke.collect.impl.hash.LHashLongSetFactoryImpl().newUpdatableSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Trove_TLongHashSet() {
        TLongHashSet set = new TLongHashSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Eclipse_LongHashSet() {
        org.eclipse.collections.impl.set.mutable.primitive.LongHashSet set = new org.eclipse.collections.impl.set.mutable.primitive.LongHashSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_LongOpenHashSet() {
        LongOpenHashSet set = new LongOpenHashSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_LongOpenHashBigSet() {
        LongOpenHashBigSet set = new LongOpenHashBigSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_LongAVLTreeSet() {
        LongAVLTreeSet set = new LongAVLTreeSet();
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_LongLinkedOpenHashSet() {
        LongLinkedOpenHashSet set = new LongLinkedOpenHashSet(size);
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public Object get_Fastutil_LongRBTreeSet() {
        LongRBTreeSet set = new LongRBTreeSet();
        for(long i = 0; i < size; i++) {
            set.add(i);
        }
        return set;
    }

    public void printSizeOfObjects(PrintWriter pw) {
        printSizeOfObject(get_Oracle_HashSet(), "Oracle_HashSet", pw);
        printSizeOfObject(get_Oracle_LinkedHashSet(), "Oracle_LinkedHashSet", pw);
        printSizeOfObject(get_Oracle_TreeSet(), "Oracle_TreeSet", pw);
        printSizeOfObject(get_HPPC_LongHashSet(), "HPPC_LongHashSet", pw);
        printSizeOfObject(get_HPPC_LongScatterSet(), "HPPC_LongScatterSet", pw);
        printSizeOfObject(get_Eclipse_LongHashSet(), "Eclipse_LongHashSet", pw);
        printSizeOfObject(get_Fastutil_LongOpenHashSet(), "Fastutil_LongOpenHashSet", pw);
        printSizeOfObject(get_Fastutil_LongOpenHashBigSet(), "Fastutil_LongOpenHashBigSet", pw);
        printSizeOfObject(get_Fastutil_LongAVLTreeSet(), "Fastutil_LongAVLTreeSet", pw);
        printSizeOfObject(get_Fastutil_LongLinkedOpenHashSet(), "Fastutil_LongLinkedOpenHashSet", pw);
        printSizeOfObject(get_Fastutil_LongRBTreeSet(), "Fastutil_LongRBTreeSet", pw);
        printSizeOfObject(get_Koloboke_MutableLongSetL(), "Koloboke_MutableLongSetL", pw);
        printSizeOfObject(get_Koloboke_MutableLongSetQ(), "Koloboke_MutableLongSetQ", pw);
        printSizeOfObject(get_Koloboke_UpdatableLongSetL(), "Koloboke_UpdatableLongSetL", pw);
        printSizeOfObject(get_Koloboke_UpdatableLongSetQ(), "Koloboke_UpdatableLongSetQ", pw);
        printSizeOfObject(get_Trove_TLongHashSet(), "Trove_TIntHashSet", pw);
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
            TestSizeLongSets test = new TestSizeLongSets(size);
            pw.println("-----------------------------" + size + "--------------------------------");
            test.printSizeOfObjects(pw);
            System.gc();
        }
    }
}

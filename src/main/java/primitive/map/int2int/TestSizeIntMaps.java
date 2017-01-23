package primitive.map.int2int;

import com.carrotsearch.hppc.IntIntScatterMap;
import com.koloboke.collect.map.hash.HashIntIntMap;
import com.koloboke.collect.map.hash.HashIntIntMaps;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import it.unimi.dsi.fastutil.ints.*;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Sergey.Voronezhtsev on 20/1/2017.
 */
public class TestSizeIntMaps {
    private int size;

    private TestSizeIntMaps(int size) {
        this.size = size;
    }

    private Object get_Oracle_array() {
        int array[] = new int[2*size];
        for(int i = 0; i < 2*size; i++) {
            array[i] = i;
        }
        return array;
    }

    private Object get_Oracle_HashMap() {
        Map<Integer,Integer> map = new HashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_Oracle_TreeMap() {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_Oracle_LinkedHashMap() {
        Map<Integer,Integer> map = new LinkedHashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_FastUtil_Int2IntOpenHashMap() {
        Int2IntOpenHashMap map = new Int2IntOpenHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_FastUtil_Int2IntLinkedOpenHashMap() {
        Int2IntLinkedOpenHashMap map = new Int2IntLinkedOpenHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_FastUtil_Int2IntAVLTreeMap() {
        Int2IntAVLTreeMap map = new Int2IntAVLTreeMap();
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_FastUtil_Int2IntRBTreeMap() {
        Int2IntRBTreeMap map = new Int2IntRBTreeMap();
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_HPPC_IntIntHashMap() {
        com.carrotsearch.hppc.IntIntHashMap map = new com.carrotsearch.hppc.IntIntHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_HPPC_IntIntScatterMap() {
        IntIntScatterMap map = new IntIntScatterMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_Koloboke_HashIntIntMap() {
        HashIntIntMap map = HashIntIntMaps.getDefaultFactory().newMutableMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_Trove_TIntIntHashMap() {
        TIntIntMap map = new TIntIntHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private Object get_Eclipse_IntIntHashMap() {
        IntIntHashMap map = new IntIntHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    private void printSizeOfObjects(PrintWriter pw) {
        printSizeOfObject(get_Oracle_HashMap(), "Oracle_HashMap", pw);
        printSizeOfObject(get_Oracle_LinkedHashMap(), "Oracle_LinkedHashMap", pw);
        printSizeOfObject(get_Oracle_TreeMap(), "Oracle_TreeMap", pw);
        printSizeOfObject(get_HPPC_IntIntHashMap(), "HPPC_IntIntHashMap", pw);
        printSizeOfObject(get_HPPC_IntIntScatterMap(), "HPPC_IntIntScatterMap", pw);
        printSizeOfObject(get_Eclipse_IntIntHashMap(), "Eclipse_IntIntHashMap", pw);
        printSizeOfObject(get_FastUtil_Int2IntOpenHashMap(), "FastUtil_Int2IntOpenHashMap", pw);
        printSizeOfObject(get_FastUtil_Int2IntLinkedOpenHashMap(), "FastUtil_Int2IntLinkedOpenHashMap", pw);
        printSizeOfObject(get_FastUtil_Int2IntAVLTreeMap(), "FastUtil_Int2IntAVLTreeMap", pw);
        printSizeOfObject(get_FastUtil_Int2IntRBTreeMap(), "FastUtil_Int2IntRBTreeMap", pw);
        printSizeOfObject(get_Koloboke_HashIntIntMap(), "Koloboke_HashIntIntMap", pw);
        printSizeOfObject(get_Trove_TIntIntHashMap(), "Trove_TIntIntHashMap", pw);
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
            TestSizeIntMaps test = new TestSizeIntMaps(size);
            pw.println("-----------------------------" + size + "--------------------------------");
            test.printSizeOfObjects(pw);
            System.gc();
        }
    }

}

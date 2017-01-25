package primitive.map.int2int;

import com.carrotsearch.hppc.IntIntScatterMap;
import com.koloboke.collect.map.hash.HashIntIntMap;
import com.koloboke.collect.map.hash.HashIntIntMaps;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import it.unimi.dsi.fastutil.ints.*;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeIntMaps extends TestSize {

    private TestSizeIntMaps(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_array() {
        int array[] = new int[2*size];
        for(int i = 0; i < 2*size; i++) {
            array[i] = i;
        }
        return array;
    }

    @Size
    public Object Oracle_HashMap() {
        Map<Integer,Integer> map = new HashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object Oracle_TreeMap() {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object Oracle_LinkedHashMap() {
        Map<Integer,Integer> map = new LinkedHashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2IntOpenHashMap() {
        Int2IntOpenHashMap map = new Int2IntOpenHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2IntLinkedOpenHashMap() {
        Int2IntLinkedOpenHashMap map = new Int2IntLinkedOpenHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2IntAVLTreeMap() {
        Int2IntAVLTreeMap map = new Int2IntAVLTreeMap();
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2IntRBTreeMap() {
        Int2IntRBTreeMap map = new Int2IntRBTreeMap();
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object HPPC_IntIntHashMap() {
        com.carrotsearch.hppc.IntIntHashMap map = new com.carrotsearch.hppc.IntIntHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object HPPC_IntIntScatterMap() {
        IntIntScatterMap map = new IntIntScatterMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object Koloboke_HashIntIntMap() {
        HashIntIntMap map = HashIntIntMaps.getDefaultFactory().newMutableMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object Trove_TIntIntHashMap() {
        TIntIntMap map = new TIntIntHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    @Size
    public Object Eclipse_IntIntHashMap() {
        IntIntHashMap map = new IntIntHashMap(size);
        for(int i = 0; i < size; i++) {
            map.put(i,i);
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeIntMaps.class);
    }

}

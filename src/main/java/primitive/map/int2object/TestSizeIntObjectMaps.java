package primitive.map.int2object;

import com.carrotsearch.hppc.IntObjectScatterMap;
import com.koloboke.collect.map.hash.HashIntObjMap;
import com.koloboke.collect.map.hash.HashIntObjMaps;
import gnu.trove.map.hash.TIntObjectHashMap;
import it.unimi.dsi.fastutil.ints.*;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeIntObjectMaps extends TestSize {

    public TestSizeIntObjectMaps(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_HashMap() {
        Map<Integer,Object> map = new HashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Oracle_TreeMap() {
        Map<Integer,Object> map = new TreeMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Oracle_LinkedHashMap() {
        Map<Integer,Object> map = new LinkedHashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Hppc_IntObjectHashMap() {
        com.carrotsearch.hppc.IntObjectHashMap<Object> map = new com.carrotsearch.hppc.IntObjectHashMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Hppc_IntObjectScatterMap() {
        IntObjectScatterMap<Object> map = new IntObjectScatterMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Trove_TIntObjectHashMap() {
        TIntObjectHashMap<Object> map = new TIntObjectHashMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Eclipse_IntObjectHashMap() {
        IntObjectHashMap<Object> map = new IntObjectHashMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectOpenHashMap() {
        Int2ObjectOpenHashMap<Object> map = new Int2ObjectOpenHashMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectRBTreeMap() {
        Int2ObjectRBTreeMap<Object> map = new Int2ObjectRBTreeMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectAVLTreeMap() {
        Int2ObjectAVLTreeMap<Object> map = new Int2ObjectAVLTreeMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectLinkedOpenHashMap() {
        Int2ObjectLinkedOpenHashMap<Object> map = new Int2ObjectLinkedOpenHashMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Koloboke_MutableHashIntObjMap() {
        HashIntObjMap<Object> map = HashIntObjMaps.getDefaultFactory().newMutableMap();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    @Size
    public Object Koloboke_UpdatableHashIntObjMap() {
        HashIntObjMap<Object> map = HashIntObjMaps.getDefaultFactory().newUpdatableMap();
        for(int i = 0; i < size; i++) {
            map.put(i,new Object());
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeIntObjectMaps.class);
    }

}

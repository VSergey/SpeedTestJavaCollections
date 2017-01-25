package primitive.map.int2string;

import com.carrotsearch.hppc.IntObjectScatterMap;
import com.koloboke.collect.map.hash.HashIntObjMap;
import com.koloboke.collect.map.hash.HashIntObjMaps;
import gnu.trove.map.hash.TIntObjectHashMap;
import it.unimi.dsi.fastutil.ints.*;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeIntStringMaps extends TestSize {

    public TestSizeIntStringMaps(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_HashMap() {
        Map<Integer,String> map = new HashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Oracle_TreeMap() {
        Map<Integer,String> map = new TreeMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Oracle_LinkedHashMap() {
        Map<Integer,String> map = new LinkedHashMap<>(size);
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Hppc_LongLongHashMap() {
        com.carrotsearch.hppc.IntObjectHashMap<String> map = new com.carrotsearch.hppc.IntObjectHashMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Hppc_LongLongScatterMap() {
        IntObjectScatterMap<String> map = new IntObjectScatterMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Trove_TIntObjectHashMap() {
        TIntObjectHashMap<String> map = new TIntObjectHashMap<>();
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Eclipse_IntObjectHashMap() {
        IntObjectHashMap<String> map = new IntObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectOpenHashMap() {
        Int2ObjectOpenHashMap<String> map = new Int2ObjectOpenHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectRBTreeMap() {
        Int2ObjectRBTreeMap<String> map = new Int2ObjectRBTreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectAVLTreeMap() {
        Int2ObjectAVLTreeMap<String> map = new Int2ObjectAVLTreeMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object FastUtil_Int2ObjectLinkedOpenHashMap() {
        Int2ObjectLinkedOpenHashMap<String> map = new Int2ObjectLinkedOpenHashMap<>();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Koloboke_MutableHashIntObjMap() {
        HashIntObjMap<String> map = HashIntObjMaps.getDefaultFactory().newMutableMap();
        for(int i = 0; i < size; i+=2) {
            map.put(i,String.valueOf(i));
        }
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    @Size
    public Object Koloboke_UpdatableHashIntObjMap() {
        HashIntObjMap<String> map = HashIntObjMaps.getDefaultFactory().newUpdatableMap();
        for(int i = 0; i < size; i++) {
            map.put(i,String.valueOf(i));
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeIntStringMaps.class);
    }

}

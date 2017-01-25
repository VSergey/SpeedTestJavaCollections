package objects.map;

import com.carrotsearch.hppc.*;
import com.google.common.collect.HashBiMap;
import com.koloboke.collect.map.hash.HashObjObjMaps;
import gnu.trove.map.hash.THashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeMap extends TestSize {

    public TestSizeMap(Integer size) {
        super(size);
    }

    private Map<String,Object> fillMap(Map<String, Object> map) {
        for(int i = 0; i < size; i+=2) {
            String key = Integer.toString(i);
            map.put(key, new Object());
        }
        for(int i = 0; i < size; i++) {
            String key = Integer.toString(i);
            map.put(key, new Object());
        }
        return map;
    }

    @Size
    public Object Oracle_HashMap() {
        Map<String,Object> map = new HashMap<>();
        return fillMap(map);
    }

    @Size
    public Object Oracle_TreeMap() {
        Map<String,Object> map = new TreeMap<>();
        return fillMap(map);
    }

    @Size
    public Object Oracle_LinkedMap() {
        Map<String,Object> map = new LinkedHashMap<>();
        return fillMap(map);
    }

    @Size
    public Object Apache_HashedMap() {
        Map<String,Object> map = new HashedMap<>();
        return fillMap(map);
    }

    @Size
    public Object Apache_LinkedMap() {
        Map<String,Object> map = new LinkedMap<>();
        return fillMap(map);
    }

    @Size
    public Object Apache_PatriciaTrie() {
        Map<String,Object> map = new PatriciaTrie<>();
        return fillMap(map);
    }

    @Size
    public Object Eclipse_UnifiedMap() {
        Map<String,Object> map = new UnifiedMap<>();
        return fillMap(map);
    }

    @Size
    public Object Eclipse_SortedTreeMap() {
        Map<String,Object> map = new TreeSortedMap<>();
        return fillMap(map);
    }

    @Size
    public Object Google_HashBiMap() {
        Map<String,Object> map = HashBiMap.create();
        return fillMap(map);
    }

    @Size
    public Object Koloboke_MutableHashObjObjMap() {
        Map<String,Object> map = HashObjObjMaps.getDefaultFactory().newMutableMap();
        return fillMap(map);
    }

    @Size
    public Object Koloboke_UpdatableHashObjObjMap() {
        Map<String,Object> map = HashObjObjMaps.getDefaultFactory().newUpdatableMap();
        return fillMap(map);
    }

    @Size
    public Object Hppc_ObjectHashMap() {
        ObjectObjectMap<String,Object> map = new ObjectObjectHashMap<>();
        for(int i = 0; i < size; i+=2) {
            String key = Integer.toString(i);
            map.put(key, new Object());
        }
        for(int i = 0; i < size; i++) {
            String key = Integer.toString(i);
            map.put(key, new Object());
        }
        return map;
    }

    @Size
    public Object Hppc_ObjectScatterMap() {
        ObjectObjectMap<String,Object> map = new ObjectObjectScatterMap<>();
        for(int i = 0; i < size; i+=2) {
            String key = Integer.toString(i);
            map.put(key, new Object());
        }
        for(int i = 0; i < size; i++) {
            String key = Integer.toString(i);
            map.put(key, new Object());
        }
        return map;
    }

    @Size
    public Object FastUtil_Object2ObjectOpenHashMap() {
        Map<String,Object> map = new Object2ObjectOpenHashMap<>();
        return fillMap(map);
    }

    @Size
    public Object Trove_THashMap() {
        Map<String,Object> map = new THashMap<>();
        return fillMap(map);
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeMap.class);
    }
}

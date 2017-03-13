package objects.set;

import com.carrotsearch.hppc.*;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeSet extends TestSize {

    public TestSizeSet(Integer size) {
        super(size);
    }

    private Set<String> fillSet(Set<String> set) {
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        return set;
    }

    @Size
    public Object Oracle_HashSet() {
        Set<String> set = new HashSet<>();
        return fillSet(set);
    }

    @Size
    public Object Oracle_TreeSet() {
        Set<String> set = new TreeSet<>();
        return fillSet(set);
    }

    @Size
    public Object Oracle_LinkedSet() {
        Set<String> set = new LinkedHashSet<>();
        return fillSet(set);
    }

    @Size
    public Object Eclipse_UnifiedSet() {
        Set<String> set = new UnifiedSet<>();
        return fillSet(set);
    }

    @Size
    public Object Eclipse_TreeSortedSet() {
        Set<String> set = new TreeSortedSet<>();
        return fillSet(set);
    }

    @Size
    public Object Hppc_ObjectHashSet() {
        ObjectSet<String> set = new ObjectHashSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        return set;
    }

    @Size
    public Object Hppc_ObjectScatterSet() {
        ObjectSet<String> set = new ObjectScatterSet<>();
        for(int i = 0; i < size; i+=2) {
            set.add(Integer.toString(i));
        }
        for(int i = 0; i < size; i++) {
            set.add(Integer.toString(i));
        }
        return set;
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeSet.class);
    }
}

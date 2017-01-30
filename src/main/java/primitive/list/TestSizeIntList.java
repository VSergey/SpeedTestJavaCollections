package primitive.list;

import com.carrotsearch.hppc.IntArrayList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.linked.TIntLinkedList;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeIntList  extends TestSize {

    public TestSizeIntList(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_array() {
        return new int[size];
    }
    @Size
    public Object Oracle_ArrayList() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Oracle_LinkedList() {
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Hppc_IntArrayList() {
        IntArrayList list = new IntArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Trove_TIntArrayList() {
        TIntArrayList list = new TIntArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Trove_TIntLinkedList() {
        TIntLinkedList list = new TIntLinkedList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Eclipse_IntArrayList() {
        org.eclipse.collections.impl.list.mutable.primitive.IntArrayList list = new org.eclipse.collections.impl.list.mutable.primitive.IntArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object FastUtil_IntArrayList() {
        it.unimi.dsi.fastutil.ints.IntArrayList list = new it.unimi.dsi.fastutil.ints.IntArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
     public Object FastUtil_IntBigArrayBigList() {
        it.unimi.dsi.fastutil.ints.IntBigList list = new it.unimi.dsi.fastutil.ints.IntBigArrayBigList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeIntList.class);
    }

}

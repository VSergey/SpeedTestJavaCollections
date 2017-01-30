package primitive.list;

import com.carrotsearch.hppc.LongArrayList;
import gnu.trove.list.array.TLongArrayList;
import gnu.trove.list.linked.TLongLinkedList;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeLongList  extends TestSize {

    public TestSizeLongList(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_array() {
        return new long[size];
    }

    @Size
    public Object Oracle_ArrayList() {
        List<Long> list = new ArrayList<>();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Oracle_LinkedList() {
        List<Long> list = new LinkedList<>();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Hppc_LongArrayList() {
        LongArrayList list = new LongArrayList();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Trove_TLongArrayList() {
        TLongArrayList list = new TLongArrayList();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Trove_TLongLinkedList() {
        TLongLinkedList list = new TLongLinkedList();
        for(long i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object Eclipse_LongArrayList() {
        org.eclipse.collections.impl.list.mutable.primitive.LongArrayList list = new org.eclipse.collections.impl.list.mutable.primitive.LongArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object FastUtil_LongArrayList() {
        it.unimi.dsi.fastutil.longs.LongArrayList list = new it.unimi.dsi.fastutil.longs.LongArrayList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    @Size
    public Object FastUtil_LongBigArrayBigList() {
        it.unimi.dsi.fastutil.longs.LongBigList list = new it.unimi.dsi.fastutil.longs.LongBigArrayBigList();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }


    public static void main(String[] args) throws Exception {
        run(TestSizeLongList.class);
    }

}

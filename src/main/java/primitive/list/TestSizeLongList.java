package primitive.list;

import com.carrotsearch.hppc.LongArrayList;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeLongList  extends TestSize {

    public TestSizeLongList(Integer size) {
        super(size);
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

    public static void main(String[] args) throws Exception {
        run(TestSizeLongList.class);
    }

}

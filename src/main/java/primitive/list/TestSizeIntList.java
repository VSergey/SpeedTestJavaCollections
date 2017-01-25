package primitive.list;

import com.carrotsearch.hppc.IntArrayList;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeIntList  extends TestSize {

    public TestSizeIntList(Integer size) {
        super(size);
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

    public static void main(String[] args) throws Exception {
        run(TestSizeIntList.class);
    }

}

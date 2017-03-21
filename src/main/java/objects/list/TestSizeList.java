package objects.list;

import com.carrotsearch.hppc.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeList extends TestSize {

    public TestSizeList(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_ArrayList() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
        return list;
    }

    @Size
    public Object Oracle_LinkedList() {
        List<String> list = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
        return list;
    }

    @Size
    public Object Hppc_ObjectArrayList() {
        ObjectArrayList<String> list = new ObjectArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
        return list;
    }

    @Size
    public Object FastUtil_ObjectBigArrayBigList() {
        ObjectBigArrayBigList<String> list = new ObjectBigArrayBigList<>();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
        return list;
    }

    @Size
    public Object FastUtil_ObjectArrayList() {
        it.unimi.dsi.fastutil.objects.ObjectArrayList<String> list = new it.unimi.dsi.fastutil.objects.ObjectArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(Integer.toString(i));
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeList.class);
    }

}

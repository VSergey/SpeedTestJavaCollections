package primitive.list;

import com.carrotsearch.hppc.DoubleArrayList;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeDoubleList extends TestSize {

    public TestSizeDoubleList(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_ArrayList() {
        List<Double> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
        return list;
    }

    @Size
    public Object Oracle_LinkedList() {
        List<Double> list = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
        return list;
    }

    @Size
    public Object Hppc_DoubleArrayList() {
        DoubleArrayList list = new DoubleArrayList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        run(TestSizeDoubleList.class);
    }

}

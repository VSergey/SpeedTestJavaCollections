package primitive.list;

import com.carrotsearch.hppc.DoubleArrayList;
import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.list.linked.TDoubleLinkedList;
import tools.Size;
import tools.TestSize;

import java.util.*;

public class TestSizeDoubleList extends TestSize {

    public TestSizeDoubleList(Integer size) {
        super(size);
    }

    @Size
    public Object Oracle_array() {
        return new double[size];
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

    @Size
    public Object Trove_TDoubleArrayList() {
        TDoubleArrayList list = new TDoubleArrayList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
        return list;
    }

    @Size
    public Object Trove_TDoubleLinkedList() {
        TDoubleLinkedList list = new TDoubleLinkedList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
        return list;
    }

    @Size
    public Object Eclipse_DoubleLinkedList() {
        org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList list = new org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
        return list;
    }

    @Size
    public Object FastUtil_DoubleLinkedList() {
        it.unimi.dsi.fastutil.doubles.DoubleArrayList list = new it.unimi.dsi.fastutil.doubles.DoubleArrayList();
        for(int i = 0; i < size; i++) {
            double v = i/0.333;
            list.add(v);
        }
        return list;
    }

    @Size
    public Object FastUtil_DoubleBigArrayBigList() {
        it.unimi.dsi.fastutil.doubles.DoubleBigList list = new it.unimi.dsi.fastutil.doubles.DoubleBigArrayBigList();
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

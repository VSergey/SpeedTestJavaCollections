package tools;

import primitive.map.int2object.TestSizeIntObjectMaps;

public class TestReferenceSize extends TestSize {

    public TestReferenceSize(Integer size) {
        super(size);
    }

    @Size
    public Object intObjectSize() {
        Object[] objects = new Object[size];
        for(int i = 0; i < size; i++) {
            objects[i] = new Object();
        }
        return new Object[] { new int[size], objects};
    }

    @Size
    public Object longObjectSize() {
        Object[] objects = new Object[size];
        for(int i = 0; i < size; i++) {
            objects[i] = new Object();
        }
        return new Object[] { new long[size], objects};
    }

    @Size
    public Object objectObjectSize() {
        Object[] objects1 = new Object[size];
        Object[] objects2 = new Object[size];
        for(int i = 0; i < size; i++) {
            objects1[i] = new Object();
            objects2[i] = new Object();
        }
        return new Object[] { objects1, objects2};
    }

    public static void main(String[] args) throws Exception {
        run(TestReferenceSize.class);
    }

}

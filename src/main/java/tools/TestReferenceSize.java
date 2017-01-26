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

    public static void main(String[] args) throws Exception {
        run(TestReferenceSize.class);
    }

}

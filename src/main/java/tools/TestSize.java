package tools;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;
import primitive.map.int2string.TestSizeIntStringMaps;

import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.*;

public class TestSize {
    protected int size;

    public TestSize(Integer size) {
        this.size = size;
    }

    protected void printSizeOfObjects(PrintWriter pw) {
        pw.println("-----------------------------" + size + "--------------------------------");
        List<Method> methods = enumerateTestMethods(this.getClass());
        for(Method m: methods) {
            try {
                Object result = m.invoke(this);
                printSizeOfObject(result, m.getName(), pw);
            } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        System.gc();
    }

    public static void run(Class<? extends TestSize> clazz) {
        System.out.println(VM.current().details());
        PrintWriter pw = new PrintWriter(System.out, true);
        int sizes[] = new int[] {50000, 100000, 500000, 1000000};
//        int sizes[] = new int[] {1000000, 5000000};

        try {
            Constructor<? extends TestSize> constructor = clazz.getConstructor(Integer.class);
            for (int size : sizes) {
                TestSize test = constructor.newInstance(size);
                test.printSizeOfObjects(pw);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private static void printSizeOfObject(Object obj, String name, PrintWriter pw) {
        GraphLayout graphLayout = GraphLayout.parseInstance(new Object[]{obj});
//        pw.println(graphLayout.toPrintable());
        pw.print(name);
        pw.print("\t\t");
        pw.print(graphLayout.totalSize());
        pw.print(" bytes / ");
        pw.print(graphLayout.totalCount());
        pw.println(" objects");
    }

    private static List<Method> enumerateTestMethods(Class<?> clazz) {
        List<Method> result = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null) {
            for(Method m : current.getDeclaredMethods()) {
                if(m.getAnnotation(Size.class) != null) {
                    result.add(m);
                }
            }
            current = current.getSuperclass();
        }
        return result;
    }

}

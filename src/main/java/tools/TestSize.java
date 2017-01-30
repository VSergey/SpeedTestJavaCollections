package tools;

import org.openjdk.jol.info.*;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.*;

public class TestSize {
    protected int size;

    public TestSize(Integer size) {
        this.size = size;
    }

    private void printSizeOfObjects(PrintWriter pw) {
        pw.println("-----------------------------" + size + "--------------------------------");
        List<Method> methods = enumerateTestMethods(this.getClass());
        methods.sort(new MethodComparator());
        for(Method m: methods) {
//            pw.print("Free memory (MB): "); pw.println(Runtime.getRuntime().freeMemory()/1024/1024);
            try {
                Object result = m.invoke(this);
                printSizeOfObject(result, m.getName(), pw);
            } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                e.printStackTrace();
            }
//            pw.print("Free memory (MB): "); pw.println(Runtime.getRuntime().freeMemory()/1024/1024);
            System.gc();
        }
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
        pw.print(name);
        pw.print("\t\t");
        try {
            SmallGraphLayout graphLayout = SmallGraphLayout.parseInstance(obj);
            pw.print(graphLayout.totalSize());
            pw.print(" bytes / ");
            pw.print(graphLayout.totalCount());
            pw.println(" objects");
        } catch (OutOfMemoryError e) {
//            e.printStackTrace();
            pw.println(" OutOfMemoryError");
        }
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

    class MethodComparator implements Comparator<Method> {
        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}

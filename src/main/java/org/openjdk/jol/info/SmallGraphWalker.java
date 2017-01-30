package org.openjdk.jol.info;

import org.openjdk.jol.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Basic class to walk object graphs.
 */
public class SmallGraphWalker {
    private final Set<Object> visited;
    private final Object[] roots;
    private final Collection<GraphVisitor> visitors;

    public SmallGraphWalker(Object... roots) {
        this.roots = roots;
        this.visited = Collections.newSetFromMap(new IdentityHashMap<>());
        this.visitors = new ArrayList<>();
    }

    public void addVisitor(GraphVisitor v) {
        visitors.add(v);
    }

    public void walk() {
        List<GraphPathRecord> curLayer = new ArrayList<>();
        List<GraphPathRecord> newLayer = new ArrayList<>();

        int rootId = 1;
        boolean single = (roots.length == 1);
        for (Object root : roots) {
            String label = single ? "" : ("<r" + rootId + ">");
            GraphPathRecord e = new GraphPathRecord(label, 0, root);
            visited.add(root);
            visitObject(e);
            curLayer.add(e);
            rootId++;
        }

        while (!curLayer.isEmpty()) {
            newLayer.clear();
            for (GraphPathRecord next : curLayer) {
                for (GraphPathRecord ref : peelReferences(next)) {
                    if (visited.add(ref.obj())) {
                        visitObject(ref);
                        newLayer.add(ref);
                    }
                }
            }
            curLayer.clear();
            curLayer.addAll(newLayer);
        }
    }

    private void visitObject(GraphPathRecord record) {
        for (GraphVisitor v : visitors) {
            v.visit(record);
        }
    }

    private List<GraphPathRecord> peelReferences(GraphPathRecord r) {
        Object o = r.obj();

        if (o == null) {
            // Nothing to do here
            return Collections.emptyList();
        }

        if (o.getClass().isArray() && o.getClass().getComponentType().isPrimitive()) {
            // Nothing to do here
            return Collections.emptyList();
        }

        List<GraphPathRecord> result = new ArrayList<GraphPathRecord>();

        if (o.getClass().isArray()) {
            int c = 0;
            for (Object e : (Object[]) o) {
                if (e != null) {
                    String path = r.path() + "[" + c + "]";
                    result.add(new GraphPathRecord(path, r.depth() + 1, e));
                }
                c++;
            }
        } else {
            for (Field f : getAllReferences(o.getClass())) {
                Object e = ObjectUtils.value(o, f);
                if (e != null) {
                    String path = r.path() + "." + f.getName();
                    if(path.length() > 100) path = path.substring(0,97) + "...";
                    result.add(new GraphPathRecord(path, r.depth() + 1, e));
                }
            }
        }

        return result;
    }

    private Collection<Field> getAllReferences(Class<?> klass) {
        List<Field> results = new ArrayList<Field>();

        for (Field f : klass.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            if (f.getType().isPrimitive()) continue;
            results.add(f);
        }

        Class<?> superKlass = klass;
        while ((superKlass = superKlass.getSuperclass()) != null) {
            for (Field f : superKlass.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                if (f.getType().isPrimitive()) continue;
                results.add(f);
            }
        }

        return results;
    }
}

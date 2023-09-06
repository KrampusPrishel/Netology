import java.util.*;

public class DAG<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> from, Vertex<T> to) {
        from.getAdjacent().add(to);
    }

    public int path(Vertex<T> from, Vertex<T> to) {
        Map<Vertex<T>, Integer> paths = new HashMap<>();
        paths.put(from, 0);

        Queue<Vertex<T>> queue = new ArrayDeque<>();
        Set<Vertex<T>> added = new HashSet<>();
        queue.add(from);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();
            if (v.equals(to)) {
                return paths.get(v);
            } else {
                for (Vertex<T> next : v.getAdjacent()) {
                    if (added.contains(next)) {
                        continue;
                    } else {
                        added.add(next);
                        paths.put(next, paths.get(v) + 1);
                        queue.add(next);
                    }
                }
            }
        }

        return -1;
    }

}
package identifierscanner.dfa;

import java.util.ArrayList;
import java.util.Iterator;

public class EdgeList implements Iterable<Edge> {
    private ArrayList<Edge> edges;

    public EdgeList() {
        edges = new ArrayList<Edge>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public Iterator<Edge> iterator() {
        return edges.iterator();
    }

    public boolean isEmpty() {
        return edges.isEmpty();
    }

    public boolean containsEdge(String word) {
        for(Edge edge: edges) {
            if(edge.getIn().equals(word)) {
                return true;
            }
        }
        return false;
    }
}

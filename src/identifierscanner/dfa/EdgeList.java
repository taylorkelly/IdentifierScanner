package identifierscanner.dfa;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A List of Edges. It is iterable over the edges and has a few helpful
 * functions.
 * Could be expanded to find applicable edges in a more optimal manner
 * @author taylor
 */
public class EdgeList implements Iterable<Edge> {
    private ArrayList<Edge> edges;

    /**
     * Initializes the internal list of edges
     */
    public EdgeList() {
        edges = new ArrayList<Edge>();
    }

    /**
     * Adds an edge to the list
     * @param edge The edge to add
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    /**
     * Returns an iterator over the edges
     * @return an iterator over the edges
     */
    public Iterator<Edge> iterator() {
        return edges.iterator();
    }

    /**
     * Returns if the edge list is empty or not
     * @return true if there are no edges, false if there are some
     */
    public boolean isEmpty() {
        return edges.isEmpty();
    }

    /**
     * Returns whether or not the edge list contains an edge applying to
     * the given string.
     * @param word The string to search for an acceptance
     * @return true if this edgelist contains an applicable edge, false if not
     */
    public boolean containsEdge(String word) {
        for (Edge edge : edges) {
            if (edge.accepts(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the edge that applies to the given string.
     * Warning: if no edge applies to the string, null is returned
     * @param str The string to search for an acceptance
     * @return The edge that applies to the string (null if none)
     */
    public Edge getEdge(String str) {
        for (Edge edge : edges) {
            if (edge.accepts(str)) {
                return edge;
            }
        }
        return null;
    }
}

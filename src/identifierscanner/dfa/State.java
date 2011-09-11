package identifierscanner.dfa;

/**
 *
 * @author taylor
 */
public class State {
    private boolean finalState;
    private String tokenType;
    private EdgeList edges;
    private Edge otherEdge;

    public State() {
        finalState = false;
        tokenType = null;
        edges = new EdgeList();
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Edge getEdge(String str) {
        for (Edge edge : edges) {
            if (edge.applies(str)) {
                return edge;
            }
        }
        return otherEdge;
    }

    public State addNewEdge(String str) {
        return addNewEdge(str, new State());
    }

    public State addNewEdge(String str, State toState) {
        if (!edges.containsEdge(str)) {
            edges.addEdge(new Edge(this, toState, str));
            return toState;
        }
        return null;
    }

    public void addOtherEdge(State idAccept) {
        otherEdge = new Edge(this, idAccept, null);
    }
}


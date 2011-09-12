package identifierscanner.dfa;

/**
 * Represents a State in a DFA
 * Knows if it is a final state, and also what type of token it applies to
 * (mainly for if a final state)
 * It contains an EdgeList holding the edges that connects it to other states
 * As well as an 'otherEdge' with can be used as an edge that is applied
 * if no applicable edge connection is found.
 */
public class State {
    private boolean finalState;
    private String tokenType;
    private EdgeList edges;
    private Edge otherEdge;

    /**
     * Initializes the State with an empty edgeList, not being a final state,
     * and not having an otherEdge
     */
    public State() {
        finalState = false;
        tokenType = null;
        edges = new EdgeList();
    }

    /**
     * Sets whether the state is a final state or not
     * @param finalState true if it is a final state, false if not
     */
    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }

    /**
     * Whether or not the state is final or not.
     * @return true if it is final, false if not
     */
    public boolean isFinalState() {
        return finalState;
    }

    /**
     * Set the type of state it is
     * @param tokenType the type of state
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Getter for the type of state it is
     * @return the type of state
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Returns the edge that applies to the given string.
     * If no applicable edge is found in the edgeList, it returns the otherEdge
     *
     * Warning: If no applicable edge is found and the state doesn't have an
     * otherEdge, null will be returned
     * @param str The string to search for
     * @return The edge that applies to that string
     */
    public Edge getEdge(String str) {
        Edge ret = edges.getEdge(str);

        if(ret == null)
            return otherEdge;
        else
            return ret;
    }

    /**
     * Adds a new edge connecting this state to another state.
     * Will create a new state for the edge to point to.
     * If an existing edge exists that the string applies to, it will not add
     * a new edge, and will just return the state that the existing edge points
     * to.
     * @param str The string to add a new edge on.
     * @return The state that the new edge points to.
     */
    public State addNewEdge(String str) {
        return addNewEdge(str, new State());
    }

    /**
     * Adds a new edge connecting this state to another state.
     * The edge will point the given toState.
     * If an existing edge exists that the string applies to, it will not add
     * a new edge, and will just return the state that the existing edge points
     * to.
     * @param str The string to add a new edge on.
     * @return The state that the new edge points to.
     */
    public State addNewEdge(String str, State toState) {
        if (!edges.containsEdge(str)) {
            edges.addEdge(new Edge(this, toState, str));
            return toState;
        }
        return edges.getEdge(str).getToState();
    }

    /**
     * Sets the otherEdge for this state to point to the given state
     * @param toState The State that the otherEdge should point to.
     */
    public void addOtherEdge(State toState) {
        otherEdge = new Edge(this, toState, null);
    }
}


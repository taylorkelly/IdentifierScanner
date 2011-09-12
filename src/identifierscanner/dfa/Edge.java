package identifierscanner.dfa;

/**
 * Represents an edge in the DFA. Has a from and to state, as well as the string
 * that the edge accepts as input.
 */
public class Edge {
    private String in;
    private State fromState;
    private State toState;

    /**
     * Create an Edge with the given properties
     * @param fromState The state the edge comes from
     * @param toState The state the edge is going to
     * @param in The string that the edge accepts
     */
    public Edge(State fromState, State toState, String in) {
        this.fromState = fromState;
        this.toState = toState;
        this.in = in;
    }

    /**
     * Checks if this edge accepts a given string
     * @param str The string to check if it accepts
     * @return true if the edge accepts the string, false otherwise
     */
    public boolean accepts(String str) {
        return in.equals(str);
    }

    /**
     * Returns the state that the edge goes to
     * @return The toState
     */
    public State getToState() {
        return toState;
    }
}

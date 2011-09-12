package identifierscanner.dfa;

/**
 * A Deterministic Finite Automata.
 * Pretty lame for now. Just has a start state and keeps track of it.
 * Could be expanded to do more later.
 */
public class DFA {
    private State startState;

    /**
     * Create the DFA, and initialize the start state to a new state
     */
    public DFA() {
        startState = new State();
    }

    /**
     * Getter for the start state
     * @return the start state of the DFA
     */
    public State getStartState() {
        return startState;
    }
}

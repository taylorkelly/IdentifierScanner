/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package identifierscanner.dfa;

/**
 *
 * @author taylor
 */
public class DFA {
    private State startState;

    public DFA() {
        startState = new State();
    }

    public State getStartState() {
        return startState;
    }
}

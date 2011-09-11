/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package identifierscanner.dfa;

/**
 *
 * @author taylor
 */
public class Edge {
    private String in;
    private State fromState;
    private State toState;

    public Edge(State fromState, State toState, String in) {
        this.fromState = fromState;
        this.toState = toState;
        this.in = in;
    }

    public String getIn() {
        return in;
    }

    public boolean applies(String str) {
        return in.equals(str);
    }

    public State getToState() {
        return toState;
    }
}

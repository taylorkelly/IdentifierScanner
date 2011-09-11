/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package identifierscanner;

/**
 *
 * @author taylor
 */
public class Token {
    private String value;
    private String type;

    public Token(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String toString() {
        return value + ": " + type;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}

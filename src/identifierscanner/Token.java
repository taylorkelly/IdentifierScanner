package identifierscanner;

/**
 * An immutable type Token, holding a token value and token type.
 * Represents the tokens being taken from source code.
 */
public class Token {
    private final String value;
    private final String type;

    /**
     * Create a Token!
     * @param value The literal value of the token
     * @param type The type of the token
     */
    public Token(String value, String type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Returns a readable format of the token, in the format:
     * "(value: type)"
     * @return The string representation of the Token
     */
    @Override
    public String toString() {
        return "(" + value + ": " + type + ")";
    }

    /**
     * Getter for the value
     * @return The literal value of the token
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for the type
     * @return The token type of the token
     */
    public String getType() {
        return type;
    }
}

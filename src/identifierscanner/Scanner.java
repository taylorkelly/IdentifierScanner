package identifierscanner;

import identifierscanner.dfa.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class uses a DFA to get the tokens out of an InputStream (typically from
 * a file). It currently constructs the DFA in its constructor, but could
 * be expanded to have a DFA passed in or construct a DFA from a specification.
 */
public class Scanner {
    private BufferedReader charReader;
    private DFA dfa;
    private char savedChar;
    private boolean ignoreWhiteSpace;

    /**
     * Construct a Scanner using the given InputStream, and it does not ignore
     * white space tokens.
     * @param charReader the InputStreamReader to get tokens from
     */
    public Scanner(InputStreamReader charReader) {
        this(charReader, false);
    }

    /**
     * Construct a Scanner!
     * @param charReader the InputStreamReader to get tokens from
     * @param ignoreWhiteSpace whether or not "WHITESPACE" tokens should be ignored
     */
    public Scanner(InputStreamReader charReader, boolean ignoreWhiteSpace) {
        savedChar = (char) 0;
        this.ignoreWhiteSpace = ignoreWhiteSpace;
        this.charReader = new BufferedReader(charReader);

        // Construct the needed DFA.
        this.dfa = new DFA();
        State start = dfa.getStartState();


        // IDENTIFIERS
        State id1 = new State();
        State idAccept = new State();
        id1.addOtherEdge(idAccept);
        idAccept.setFinalState(true);
        idAccept.setTokenType("IDENTIFIER");

        start.addNewEdge("_", id1);
        id1.addNewEdge("_", id1);
        for (char character = 'a'; character <= 'z'; character++) {
            start.addNewEdge(String.valueOf(character), id1);
            id1.addNewEdge(String.valueOf(character), id1);
        }
        for (char character = 'A'; character <= 'Z'; character++) {
            start.addNewEdge(String.valueOf(character), id1);
            id1.addNewEdge(String.valueOf(character), id1);
        }
        for (char character = '0'; character <= '9'; character++) {
            id1.addNewEdge(String.valueOf(character), id1);
        }

        // WHITE-SPACE
        State whiteSpace1 = new State();
        State whiteSpaceAccept = new State();
        whiteSpace1.addOtherEdge(whiteSpaceAccept);
        whiteSpaceAccept.setFinalState(true);
        whiteSpaceAccept.setTokenType("WHITESPACE");

        start.addNewEdge(" ", whiteSpace1);
        whiteSpace1.addNewEdge(" ", whiteSpace1);
        start.addNewEdge("\t", whiteSpace1);
        whiteSpace1.addNewEdge("\t", whiteSpace1);
        start.addNewEdge("\n", whiteSpace1);
        whiteSpace1.addNewEdge("\n", whiteSpace1);

        // OPEN-CURLY
        State openCurly1 = new State();
        State openCurlyAccept = new State();
        openCurly1.addOtherEdge(openCurlyAccept);
        openCurlyAccept.setFinalState(true);
        openCurlyAccept.setTokenType("OPENCURLY");
        start.addNewEdge("{", openCurly1);

        // CLOSE-CURLY
        State closedCurly1 = new State();
        State closedCurlyAccept = new State();
        closedCurly1.addOtherEdge(closedCurlyAccept);
        closedCurlyAccept.setFinalState(true);
        closedCurlyAccept.setTokenType("CLOSECURLY");
        start.addNewEdge("}", closedCurly1);

        // COMMA
        State comma1 = new State();
        State commaAccept = new State();
        comma1.addOtherEdge(commaAccept);
        commaAccept.setFinalState(true);
        commaAccept.setTokenType("COMMA");
        start.addNewEdge(",", comma1);

        // SEMI-COLON
        State semi1 = new State();
        State semiAccept = new State();
        semi1.addOtherEdge(semiAccept);
        semiAccept.setFinalState(true);
        semiAccept.setTokenType("SEMICOLON");
        start.addNewEdge(";", semi1);

        // CHARACTER
        State character1 = new State();
        State character2 = new State();
        State escapeCharacter = new State();
        State characterAccept = new State();
        characterAccept.setFinalState(true);
        characterAccept.setTokenType("CHARACTER");
        start.addNewEdge("\'", character1);
        character1.addNewEdge("\'", character2);
        character1.addOtherEdge(character1);
        character1.addNewEdge("\\", escapeCharacter);
        escapeCharacter.addOtherEdge(character1);
        character2.addOtherEdge(characterAccept);

        // STRING
        State string1 = new State();
        State string2 = new State();
        State escapeString = new State();
        State stringAccept = new State();
        stringAccept.setFinalState(true);
        stringAccept.setTokenType("STRING");
        start.addNewEdge("\"", string1);
        string1.addNewEdge("\"", string2);
        string1.addOtherEdge(string1);
        string1.addNewEdge("\\", escapeString);
        escapeString.addOtherEdge(string1);
        string2.addOtherEdge(stringAccept);

        // MYSTERY STATES
        State mysteryState1 = new State();
        State mysteryState2 = new State();
        mysteryState2.setFinalState(true);
        mysteryState2.setTokenType("?");
        start.addOtherEdge(mysteryState1);
        mysteryState1.addOtherEdge(mysteryState2);
    }

    /**
     * Returns whether or not the Scanner has another token to offer.
     * Checks the InputStream as to whether it is ready and has more characters
     * to offer
     * @return true if there are more tokens, false if not
     */
    public boolean hasNextToken() {
        try {
            return charReader.ready();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Returns the next token using the longest-math algorithm using a Token
     * object, holding its value and token type.
     * @return The Token object representing the Token ripped from the
     * @throws IOException If there is a problem reading the InputStream
     */
    public Token nextToken() throws IOException {
        State currState = dfa.getStartState();
        StringBuilder tokenValue = new StringBuilder();
        char currentChar = (char) 0;

        while (!currState.isFinalState()) {
            if (savedChar != 0) {
                currentChar = savedChar;
                savedChar = (char) 0;
            } else {
                currentChar = (char) charReader.read();
            }
            tokenValue.append(currentChar);
            Edge travelEdge = currState.getEdge(String.valueOf(currentChar));
            currState = travelEdge.getToState();
        }

        if (currentChar != 0) {
            savedChar = currentChar;
            tokenValue.deleteCharAt(tokenValue.length() - 1);
        }

        String tokenType = currState.getTokenType();
        Token token = new Token(tokenValue.toString(), tokenType);

        if (tokenType.equals("IDENTIFIER")) {
            token = ReservedWords.checkReservedWords(token);
        }

        if (tokenType.equals("WHITESPACE") && ignoreWhiteSpace) {
            return this.nextToken();
        }

        return token;

    }
}

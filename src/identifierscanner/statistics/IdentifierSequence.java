package identifierscanner.statistics;

import identifierscanner.Token;
import identifierscanner.util.BasicEntry;
import identifierscanner.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * A Class that takes in tokens and stores the identifiers and braces in a list,
 * keeping track of specific counts:
 * - Overall identifier count
 * - Count per identifier
 * - Count per identifier per scope
 * - Count per identifier pair
 */
public class IdentifierSequence {
    public LinkedList<Token> tokenSequence;
    private int identifierCount;
    private HashMap<String, Integer> identifierCounts;
    private HashMap<Integer, HashMap<String, Integer>> scopeIdentifierCounts;
    private int currScope;
    private HashMap<Pair<String>, Integer> pairCounts;
    private String pastIdentifier;

    /**
     * Initializes all of the internal variables
     */
    public IdentifierSequence() {
        identifierCount = 0;
        currScope = 0;
        identifierCounts = new HashMap<String, Integer>();
        scopeIdentifierCounts = new HashMap<Integer, HashMap<String, Integer>>();
        tokenSequence = new LinkedList<Token>();
        pairCounts = new HashMap<Pair<String>, Integer>();
    }

    /**
     * Takes in a token for analysis and determine what counts it applies to
     * @param token The token for IdentifierSequence to take in.
     */
    public void takeToken(Token token) {
        String tokenType = token.getType();

        if (tokenType.equals("OPENCURLY") || tokenType.equals("CLOSECURLY"))
            updateScope(tokenType);
        else if (tokenType.equals("IDENTIFIER")) {
            countIdentifier(token.getValue());
        }

        tokenSequence.add(token);
    }

    /**
     * Private helped method for updating the scope depending on if the token
     * type is an open curly or closed curly
     * @param type the token type of the affecting token
     * @return the new scope
     */
    private int updateScope(String type) {
        if (type.equals("OPENCURLY"))
            currScope++;
        else if (type.equals("CLOSECURLY"))
            currScope--;

        return currScope;
    }

    /**
     * Counts an identifier token's value. This'll update all the internal counts
     * Precondition: The value is verified to be an IDENTIFIER.
     * @param value The value of the identifier token
     */
    private void countIdentifier(String value) {
        identifierCount++;

        int idCount = 0;
        if (identifierCounts.containsKey(value))
            idCount = identifierCounts.get(value);

        identifierCounts.put(value, idCount + 1);


        int scopeIdCount = 0;
        if (!scopeIdentifierCounts.containsKey(currScope))
            scopeIdentifierCounts.put(currScope, new HashMap<String, Integer>());
        if (scopeIdentifierCounts.get(currScope).containsKey(value))
            scopeIdCount = identifierCounts.get(value);

        scopeIdentifierCounts.get(currScope).put(value, scopeIdCount + 1);

        if (pastIdentifier != null) {
            Pair pair = new Pair(pastIdentifier, value);
            int pairCount = 0;
            if (pairCounts.containsKey(pair))
                pairCount = pairCounts.get(pair);

            pairCounts.put(pair, pairCount + 1);
        }
        pastIdentifier = value;
    }

    /**
     * Returns a list of the top ten identifiers (sorted from most to "least")
     * Each element in the list is an entry matching the value of the identifier
     * to the count of said identifier
     *
     * Warning: This is not guaranteed to have 10 elements in the list
     * (ie. if there are less than 10 identifiers to examine)
     * @return The list containing the top 10 identifiers and their counts
     */
    public List<Entry<String, Integer>> topTenIdentifiers() {
        ValueComparator comparator = new ValueComparator(identifierCounts);
        TreeMap<String, Integer> sortedIdentifiers = new TreeMap(comparator);

        sortedIdentifiers.putAll(identifierCounts);

        ArrayList<Entry<String, Integer>> topTen = new ArrayList<Entry<String, Integer>>(10);

        int count = 0;
        for (Entry<String, Integer> entry : sortedIdentifiers.entrySet()) {
            if (count == 10) break;
            topTen.add(entry);
            count++;
        }

        return topTen;
    }

    /**
     * Returns a list of the top ten pairs of identifiers (sorted from most to
     * "least")
     * Each element in the list is an entry matching the values of the identifiers
     * to the count of said identifier pair
     *
     * Warning: This is not guaranteed to have 10 elements in the list
     * (ie. if there were less than 10 pairs to examine).
     * @return The list containing the top 10 identifier pairs and their counts
     */
    public List<Entry<Pair<String>, Integer>> topTenPairs() {
        ValueComparator comparator = new ValueComparator(pairCounts);
        TreeMap<Pair<String>, Integer> sortedIdentifiers = new TreeMap(comparator);

        sortedIdentifiers.putAll(pairCounts);

        ArrayList<Entry<Pair<String>, Integer>> topTen = new ArrayList<Entry<Pair<String>, Integer>>(10);

        int count = 0;
        for (Entry<Pair<String>, Integer> entry : sortedIdentifiers.entrySet()) {
            if (count == 10) break;
            topTen.add(entry);
            count++;
        }

        return topTen;
    }

    /**
     * Returns the scope with the most unique identifiers
     * @return The number of the scope with the most unique identifiers
     */
    public int topScope() {
        int maxScope = -1;
        int maxCount = -1;

        for (Entry<Integer, HashMap<String, Integer>> entry :
                scopeIdentifierCounts.entrySet()) {
            if (entry.getValue().size() > maxCount || maxCount == -1) {
                maxCount = entry.getValue().size();
                maxScope = entry.getKey();
            }
        }

        return maxScope;
    }

    /**
     * Returns the set of unique identifiers that fall under a given scope
     * Note: If the scope does not have records for it, null will be returned
     * @param scope the scope to get the unique identifiers for
     * @return the set of values of unique identifiers
     */
    public Set<String> distinctScopeIdentifiers(int scope) {
        if (scopeIdentifierCounts.containsKey(scope)) {
            return scopeIdentifierCounts.get(scope).keySet();
        } else {
            return null;
        }
    }

    /**
     * Returns a list of the bottom ten identifiers (sorted from least to "most")
     * Each element in the list is an entry matching the value of the identifier
     * to the count of said identifier
     *
     * Warning: This is not guaranteed to have 10 elements in the list
     * (ie. if there are less than 10 identifiers to examine)
     * @return The list containing the bottom 10 identifiers and their counts
     */
    public List<Entry<String, Integer>> bottomTenIdentifiers() {
        ReverseValueComparator comparator = new ReverseValueComparator(identifierCounts);
        TreeMap<String, Integer> sortedIdentifiers = new TreeMap(comparator);

        sortedIdentifiers.putAll(identifierCounts);

        ArrayList<Entry<String, Integer>> bottomTen = new ArrayList<Entry<String, Integer>>(10);

        int count = 0;
        for (Entry<String, Integer> entry : sortedIdentifiers.entrySet()) {
            if (count == 10) break;
            bottomTen.add(entry);
            count++;
        }

        return bottomTen;
    }
}

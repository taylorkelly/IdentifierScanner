package identifierscanner.statistics;

import identifierscanner.Token;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author taylor
 */
public class IdentifierSequence {
    public LinkedList<Token> tokenSequence;
    private int identifierCount;
    private HashMap<String, Integer> identifierCounts;
    private HashMap<Integer, HashMap<String, Integer>> scopeIdentifierCounts;
    private int currScope;

    public IdentifierSequence() {
        identifierCount = 0;
        currScope = 0;
        identifierCounts = new HashMap<String, Integer>();
        scopeIdentifierCounts = new HashMap<Integer, HashMap<String, Integer>>();
        tokenSequence = new LinkedList<Token>();
    }

    public void takeToken(Token token) {
        String tokenType = token.getType();

        if (tokenType.equals("OPENCURLY") || tokenType.equals("CLOSECURLY"))
            updateScope(tokenType);
        else if (tokenType.equals("IDENTIFIER")) {
            countIdentifier(token.getValue());
        }

        tokenSequence.add(token);
    }

    private int updateScope(String type) {
        if (type.equals("OPENCURLY"))
            currScope++;
        else if (type.equals("CLOSECURLY"))
            currScope--;

        return currScope;
    }

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
    }

    public List<Entry<String, Integer>> topTenIdentifiers() {
        ValueComparator comparator = new ValueComparator(identifierCounts);
        TreeMap<String, Integer> sortedIdentifiers = new TreeMap(comparator);

        sortedIdentifiers.putAll(identifierCounts);

        ArrayList<Entry<String, Integer>> topTen = new ArrayList<Entry<String, Integer>>(10);

        int count = 0;
        for (Entry<String, Integer> entry : sortedIdentifiers.entrySet()) {
            if (count == 10) break;
            System.out.println(entry.getKey() + ": " + entry.getValue());
            topTen.add(entry);
            count++;
        }

        return topTen;
    }

    public int topScope() {
        int maxScope = -1;
        int maxCount = -1;

        for (Entry<Integer, HashMap<String, Integer>> entry :
                scopeIdentifierCounts.entrySet()) {
            if (entry.getValue().size() > maxCount || maxCount == -1) {
                maxCount = entry.getValue().size();
                maxScope = entry.getKey();
            }
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

        return maxScope;
    }

    public List<Entry<Integer, List<String>>> distinctScopeIdentifiers() {
        int maxScope = -1;
        int maxCount = -1;

        for (Entry<Integer, HashMap<String, Integer>> entry :
                scopeIdentifierCounts.entrySet()) {
            if (entry.getValue().size() > maxCount || maxCount == -1) {
                maxCount = entry.getValue().size();
                maxScope = entry.getKey();
            }
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

        return maxScope;
    }
}

class ValueComparator implements Comparator {
    Map base;

    public ValueComparator(Map base) {
        this.base = base;
    }

    public int compare(Object a, Object b) {

        if ((Integer) base.get(a) < (Integer) base.get(b)) {
            return 1;
        } else if ((Integer) base.get(a) == (Integer) base.get(b)) {
            return 0;
        } else {
            return -1;
        }
    }
}

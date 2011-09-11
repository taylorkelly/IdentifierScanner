package identifierscanner.statistics;

import identifierscanner.Token;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author taylor
 */
public class Statistics {
    private int identifierCount;
    private HashMap<String, Integer> identifierCounts;
    private HashMap<Integer, HashMap<String, Integer>> scopeIdentifierCounts;
    private int scope;

    public Statistics() {
        identifierCount = 0;
        scope = 0;
        identifierCounts = new HashMap<String, Integer>();
        scopeIdentifierCounts = new HashMap<Integer, HashMap<String, Integer>>();
    }

    public void takeToken(Token token) {
        String tokenType = token.getType();

        if (tokenType.equals("OPENCURLY") || tokenType.equals("CLOSECURLY"))
            updateScope(tokenType);
        else if (tokenType.equals("IDENTIFIER")) {
            countIdentifier(token.getValue());
        }
    }

    private int updateScope(String type) {
        if (type.equals("OPENCURLY"))
            scope++;
        else if (type.equals("CLOSECURLY"))
            scope--;

        return scope;
    }

    private void countIdentifier(String value) {
        identifierCount++;

        int idCount = 0;
        if (identifierCounts.containsKey(value))
            idCount = identifierCounts.get(value);

        identifierCounts.put(value, idCount + 1);


        int scopeIdCount = 0;
        if (!scopeIdentifierCounts.containsKey(scope))
            scopeIdentifierCounts.put(scope, new HashMap<String, Integer>());
        if (scopeIdentifierCounts.get(scope).containsKey(value))
            scopeIdCount = identifierCounts.get(value);

        scopeIdentifierCounts.get(scope).put(value, scopeIdCount + 1);
    }

    public ArrayList<Entry<String, Integer>> topTenIdentifiers() {
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

        for(Entry<Integer, HashMap<String, Integer>> entry: scopeIdentifierCounts.entrySet()) {
            if(entry.getValue().size() > maxCount || maxCount == -1) {
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

package identifierscanner;

import identifierscanner.statistics.IdentifierSequence;
import identifierscanner.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The main class that runs the Scanner and statistics on a given .c source
 * file
 */
public class Main {
    /**
     * It's a main method. If you give arguments, the first argument will be
     * used as the filepath+name. Otherwise it will just perform on
     * Gcc_Preprocessed.c that it expects to be in the same directory
     * @param args The first argument is the path to the filename (optional)
     */
    public static void main(String[] args) {
        InputStreamReader charReader;
        Scanner scan;
        IdentifierSequence stats = new IdentifierSequence();
        File file;
        try {
            if (args.length == 0) {
                file = new File("Gcc_Preprocessed.c");
                charReader = new FileReader(file);
            } else {
                file = new File(args[0]);
                charReader = new FileReader(file);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Could not find file\n" + ex.getMessage());
            return;
        }

        scan = new Scanner(charReader);

        try {
            while (scan.hasNextToken()) {
                Token token = scan.nextToken();
                stats.takeToken(token);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }



        // Print out all the stats
        System.out.println("File: " + file.getName());
        System.out.println();
        System.out.println("---------- Question 1 ----------");
        List<Entry<String, Integer>> top10Ids = stats.topTenIdentifiers();
        int i = 1;
        for (Entry<String, Integer> entry : top10Ids) {
            System.out.println(i + ". " + entry.getKey() + ": " + entry.getValue() + " occurrences");
            i++;
        }

        System.out.println();
        System.out.println("---------- Question 2 ----------");
        List<Entry<Pair<String>, Integer>> top10Pairs = stats.topTenPairs();
        i = 1;
        for (Entry<Pair<String>, Integer> entry : top10Pairs) {
            System.out.println(i + ". " + entry.getKey().toString() + ": " + entry.getValue() + " occurrences");
            i++;
        }

        System.out.println();
        System.out.println("---------- Question 3 ----------");
        List<Entry<String, Integer>> bottom10Ids = stats.bottomTenIdentifiers();
        i = 1;
        for (Entry<String, Integer> entry : bottom10Ids) {
            System.out.println(i + ". " + entry.getKey() + ": " + entry.getValue() + " occurrences");
            i++;
        }

        System.out.println();
        System.out.println("---------- Question 5 ----------");
        int topScope = stats.topScope();
        Set<String> scopeSet = stats.distinctScopeIdentifiers(topScope);
        i = 1;
        System.out.println("The top scope is level " + topScope);
        System.out.println("It has the unique ids of: ");
        for (String value : scopeSet) {
            System.out.print(value + ", ");
        }
        System.out.println();


    }
}

package identifierscanner;

import identifierscanner.statistics.Statistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author taylor
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InputStreamReader charReader;
        Scanner scan;
        Statistics stats = new Statistics();

        try {
            if (args.length == 0) {
                charReader = new FileReader(new File("Bubblesort_Preprocessed.c"));
            } else {
                charReader = new FileReader(new File(args[0]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Could not find file\n" + ex.getMessage());
            return;
        }

        scan = new Scanner(charReader, true);

        try {
            while (scan.hasNextToken()) {
                Token token = scan.nextToken();
                stats.takeToken(token);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        stats.topTenIdentifiers();
        stats.topScope();

    }
}

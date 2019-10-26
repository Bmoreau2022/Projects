import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Autocomplete {

    private final Term[] aTerm;
    private int begin;
    private int last;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {

        if (terms == null) {
            throw new IllegalArgumentException();
        }
        for (Term temp : terms) {
            if (temp == null) {
                throw new IllegalArgumentException();
            }
        }

        aTerm = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            aTerm[i] = terms[i];
        }
        Arrays.sort(aTerm);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException();
        }

        Term temp = new Term(prefix, 0);

        int begin = BinarySearchDeluxe.firstIndexOf(aTerm, temp, Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(aTerm, temp, Term.byPrefixOrder(prefix.length()));

        int index = 0;
        Term[] duplicate = new Term[last - begin + 1];
        for (int i = begin; i <= last; i++) {
            duplicate[index++] = aTerm[i];
        }

        Arrays.sort(duplicate, Term.byReverseWeightOrder());
        return duplicate;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException();
        }

        Term temp = new Term(prefix, 0);

        int begin = BinarySearchDeluxe.firstIndexOf(aTerm, temp, Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(aTerm, temp, Term.byPrefixOrder(prefix.length()));

        return last - begin + 1;
    }

    // unit testing (required)
    public static void main(String[] args) {

        // read in the terms from a file
        String filename = "movies.txt";
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = 100;
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}

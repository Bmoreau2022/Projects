import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {

    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {

        if (query == null) {
            throw new IllegalArgumentException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        this.weight = weight;
        this.query = query;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new Reverse();
    }

    private static class Reverse implements Comparator<Term> {
        public int compare(Term a, Term b) {
            if (a.weight == b.weight) {
                return 0;
            }
            if (a.weight < b.weight) {
                return 1;
            } else {
                return -1;
            }
        }
    }


    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {

        if (r < 0) {
            throw new IllegalArgumentException();
        }
        return new PrefixOrder(r);

    }

    private static class PrefixOrder implements Comparator<Term> {

        private final int r;

        private PrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term a, Term b) {
            int la = a.query.length();
            int lb = b.query.length();
            if ((la >= r) && (lb >= r)) {
                String Sa = a.query.substring(0, r);
                String Sb = b.query.substring(0, r);
                return Sa.compareTo(Sb);
            } else if ((la < r) && (lb >= r)) {
                String Sa = a.query;
                String Sb = b.query.substring(0, r);
                return Sa.compareTo(Sb);
            } else if ((la >= r) && (lb < r)) {
                String Sa = a.query.substring(0, r);
                String Sb = b.query;
                return Sa.compareTo(Sb);
            } else {
                String Sa = a.query;
                String Sb = b.query;
                return Sa.compareTo(Sb);
            }
        }

    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {

        return (this.query).compareTo(that.query);

    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {

        String Weight = Long.toString(weight);
        return Weight + "     " + query;
    }

    // unit testing (required)
    public static void main(String[] args) {

        Term[] aTerms = {new Term("Nipurna", 160), new Term("Brad", 200), new Term("Nick", 150), new Term("Matt", 130), new Term("Zach", 188)};

        Arrays.sort(aTerms, Term.byReverseWeightOrder());
        for (Term temp : aTerms) {
            System.out.println(temp);
            System.out.println();
        }

        System.out.println("--------");

        Term[] bTerms = {new Term("Brett", 300), new Term("Brad", 200), new Term("Brandley", 100), new Term("Bushor", 170), new Term("Blake", 200)};

        Arrays.sort(bTerms, Term.byPrefixOrder(3));
        for (Term temp : bTerms) {
            System.out.println(temp);
            System.out.println();
        }

        System.out.println("--------");

        for (Term term : bTerms) {
            System.out.println(term);
            System.out.println();
        }

        System.out.println("--------");

        Arrays.sort(bTerms);
        for (Term term : bTerms) {
            System.out.println(term);
        }
    }
}

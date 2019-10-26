import java.util.Collections;
import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        if (a.length == 0) {
            return -1;
        }

        int hi = a.length - 1, lo = 0;
        int middle = lo + (hi - lo) / 2;
        while (hi >= lo) {

            if (comparator.compare(key, a[middle]) > 0) {
                lo = middle + 1;
            } else if (comparator.compare(key, a[middle]) < 0) {
                hi = middle - 1;
            } else if (comparator.compare(a[middle - 1], a[middle]) == 0) {
                hi = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        if (a.length == 0) {
            return -1;
        }

        int hi = a.length - 1, lo = 0;
        while (hi >= lo) {
            int middle = lo + (hi - lo) / 2;
            if (comparator.compare(key, a[middle]) > 0) {
                lo = middle + 1;
            } else if (comparator.compare(key, a[middle]) < 0) {
                hi = middle - 1;
            } else if (comparator.compare(a[middle + 1], a[middle]) == 0) {
                lo = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {

        Integer[] numb = {9, 9, 7, 7, 5, 5, 9, 3, 3, 8, 1, 1};
        System.out.println(BinarySearchDeluxe.firstIndexOf(numb, 9, Collections.reverseOrder()) + "\t");
        System.out.println(BinarySearchDeluxe.lastIndexOf(numb, 9, Collections.reverseOrder()));
    }
}

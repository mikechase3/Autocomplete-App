/**
 * Mike Chase | Josh Cowden | Dai YuQin
 * Updated March 4, 2020 at 9:56 PM
 *
 * The BinarySearchDeluxe class takes the user provided keys and returns a range of the matching keys.
 */
package Autocomplete;
import java.rmi.NoSuchObjectException;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 1. Return -1 or something if the result isn't in there.
 * 2. At the end,
 */
public class BinarySearchDeluxe {
    //Member Variables


    //Constructor
    public BinarySearchDeluxe() {

    }

    //Member Methods
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) throws NoSuchObjectException {
        System.out.print("BinarySearchDeluxe.firstIndexOf: ");
        System.out.println("key = " + key);
        int low = 0; //Sets an initial value of low.
        int high = a.length - 1; //Sets an initial value of high for the binarysearch recursive method.
        int firstIndex = -1; //-1 will be returned if nothing is found.
        int returned = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex); //Begin recursive method.

        if (returned == -1) {
            System.out.println("BinarySearchDeluxe.firstIndexOf");
            throw new NoSuchElementException("The object does not exist.");
        }

        return returned;


    }

    private static <Key> int recursiveFirstIndexOf(Key[] a, Key key, Comparator<Key> comparator, int low, int high, int firstIndex) {
        int midIndex = low + ((high - low) / 2);

        if (high <= low) {
            if (comparator.compare(key, a[midIndex]) == 0) {
                return midIndex;
            }
            return firstIndex;
        }

        /**
         * If the mid-index is already in the middle and it's the same, keep backtracking until you find the first one.
         */
        if (comparator.compare(key, a[midIndex]) == 0) {

            high = midIndex;
            firstIndex = midIndex;
            firstIndex = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex);
        }
        /**
         * If the provided key is larger than the middle index, eliminate half the array and check the midpoint again.
         */
        else if (comparator.compare(key, a[midIndex]) == 1) {
            low = midIndex + 1; // +1 because arrays start at zero.
            firstIndex = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex);
        } else if (comparator.compare(key, a[midIndex]) == -1) {
            high = midIndex;
            firstIndex = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex);
        }
        return firstIndex; //Will return -1 (from the beginning) if nothing was found.
    }


    /**
     * Calls a recursive binary search function to find the index of the object. Verifies it exists
     *
     * @param a
     * @param key
     * @param comparator
     * @param <Key>
     * @return
     * @throws NoSuchObjectException if the object is not found.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) throws NoSuchObjectException {
        System.out.println("BinarySearchDeluxe.lastIndexOf");
        int low = 0; //Sets an initial value of low.
        int high = a.length - 1; //Sets an initial value of high for the binarysearch recursive method.
        int firstIndex = -1; //-1 will be returned if nothing is found.
        int returned = lastIndexOfRecursive(a, key, comparator, low, high, firstIndex); //Begin recursive method.

        if (returned == -1) {
            System.out.println("BinarySearchDeluxe.lastIndexOf");
            throw new NoSuchElementException("The object does not exist.");
        }

        return returned;
    }

    public static <Key> int lastIndexOfRecursive(Key[] a, Key key, Comparator<Key> comparator, int low, int high, int firstIndex) {
        int midIndex = low + ((high - low) / 2);

        if (high <= low) {
            if (comparator.compare(key, a[midIndex]) == 0) {
                return midIndex;
            }
            return firstIndex;
        }

        /**
         * If the mid-index is already in the middle and it's the same, keep backtracking until you find the first one.
         */
        if (comparator.compare(key, a[midIndex]) == 0) {

            low = midIndex+1;
            firstIndex = midIndex;
            firstIndex = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex);
        }
        /**
         * If the provided key is larger than the middle index, eliminate half the array and check the midpoint again.
         */
        else if (comparator.compare(key, a[midIndex]) == 1) {
            low = midIndex + 1; // +1 because arrays start at zero.
            firstIndex = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex);

        } else if (comparator.compare(key, a[midIndex]) == -1) {
            high = midIndex;
            firstIndex = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex);
        }
        return firstIndex; //Will return -1 (from the beginning) if nothing was found.
    }
}

//    /**
//     * Returns the first numOfCharsToReturn characters of a string as a string.
//     *
//     * Example: Hello
//     */
//    private static String firstNCharacters(String input, int numOfCharsToReturn) throws IllegalArgumentException{
//        if (input.length() < 1){
//            System.out.println("BinarySearchDeluxe.firstNCharacters");
//            throw new IllegalArgumentException("String passed is empty.");
//        }
//        else{
//            return input.substring(0, numOfCharsToReturn-1);
//        }
//    }
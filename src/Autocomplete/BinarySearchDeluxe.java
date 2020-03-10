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

    /**
     * This method searches for a key in an array and returns the index of the first matching key (in the array).
     * @param a is the array that we're given.
     * @param key is the given key.
     * @param comparator and object passed that compares two Terms.
     * @param <Key> The keys used for the comparator method.
     * @return the first index of the matching key.
     * @throws NoSuchObjectException
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) throws NoSuchObjectException {
//        System.out.print("BinarySearchDeluxe.firstIndexOf: ");
//        System.out.println("key = " + key);
        int low = 0; //Sets an initial value of low.
        int high = a.length - 1; //Sets an initial value of high for the binarysearch recursive method.
        int firstIndex = -1; //-1 will be returned if nothing is found.
        int returned = recursiveFirstIndexOf(a, key, comparator, low, high, firstIndex); //Begin recursive method.

        if (returned == -1) {
            System.out.print("BinarySearchDeluxe.firstIndexOf ");
            throw new NoSuchElementException("The object does not exist.");
        }

        return returned;


    }

    /**
     * This is the recursive part of the firstIndexOf method. It will run and discard half the data over and over again
     * until it finds the firstIndex of the matching key.
     * @param a array, divided in half every time.
     * @param key is the key we're trying to find.
     * @param comparator is the object that compares two terms.
     * @param low the index of the lowest thing we're comparing the key to.
     * @param high the index of the highest thing we're comparing the key to.
     * @param firstIndex Where the first index of the given array is.
     * @param <Key> The keys used for the Comparator object
     * @return Returns the first index.
     */
    private static <Key> int recursiveFirstIndexOf(Key[] a, Key key, Comparator<Key> comparator, int low, int high, int firstIndex) {
        int midIndex = low + ((high - low) / 2);

        if (high <= low) {
            if (comparator.compare(key, a[midIndex]) == 0) {
                return midIndex;
            }
            return firstIndex;
        }

        /**
         * If the mid-index is already in the middle and it's the same, recursively go back until you find the first.
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
     * Calls a recursive binary search function to find the index of the object. Verifies it exists. (See above params).
     *
     * @param a
     * @param key
     * @param comparator
     * @param <Key>
     * @return
     * @throws NoSuchObjectException if the object is not found.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) throws NoSuchObjectException {
//        System.out.println("BinarySearchDeluxe.lastIndexOf");
        int low = 0; //Sets an initial value of low.
        int high = a.length - 1; //Sets an initial value of high for the binarysearch recursive method.
        int firstIndex = -1; //-1 will be returned if nothing is found.
        int returned = lastIndexOfRecursive(a, key, comparator, low, high, firstIndex); //Begin recursive method.

        if (returned == -1) {
            System.out.print("BinarySearchDeluxe.lastIndexOf");
            throw new NoSuchElementException("The object does not exist.");
        }

        return returned;
    }

    /**
     * The recursive part of the lastIndexOf method. This keeps calling itself until it gets the firstIndex and returns that.
     * If it returns -1, then not term exists with the specified key. See earlier methods for params.
     * @param a
     * @param key
     * @param comparator
     * @param low
     * @param high
     * @param firstIndex
     * @param <Key>
     * @return
     */
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
            firstIndex = lastIndexOfRecursive(a, key, comparator, low, high, firstIndex);
        }
        /**
         * If the provided key is larger than the middle index, eliminate half the array and check the midpoint again.
         */
        else if (comparator.compare(key, a[midIndex]) == 1) {
            low = midIndex + 1; // +1 because arrays start at zero.
            firstIndex = lastIndexOfRecursive(a, key, comparator, low, high, firstIndex);

        } else if (comparator.compare(key, a[midIndex]) == -1) {
            high = midIndex;
            firstIndex = lastIndexOfRecursive(a, key, comparator, low, high, firstIndex);
        }
        return firstIndex; //Will return -1 (from the beginning) if nothing was found.
    }
}
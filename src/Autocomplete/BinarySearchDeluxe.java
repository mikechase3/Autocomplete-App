/**
 * Mike Chase | Josh Cowden | Dai YuQin
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
    public BinarySearchDeluxe(){

    }

    //Member Methods
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) throws NoSuchObjectException{
        System.out.println("BinarySearchDeluxe.firstIndexOf");
        int low = 0; //Sets an initial value of low.
        int high = a.length-1; //Sets an initial value of high for the binarysearch recursive method.
        int returned = recursiveFirstIndexOf(a, key, comparator, low, high); //Begin recursive method.

        if (returned == -1){
            System.out.println("BinarySearchDeluxe.firstIndexOf");
            throw new NoSuchElementException("The object does not exist.");
        }

        return returned;


    }

    private static <Key> int recursiveFirstIndexOf(Key[] a, Key key, Comparator<Key> comparator, int low, int high){
        int midIndex = low + ((high-low)/2);
        int indexPosition = -1;

        /**
         * If the mid-index is already in the middle and it's the same, keep backtracking until you find the first one.
         */
        if (comparator.compare(key, a[midIndex]) == 0){
            while(comparator.compare(a[midIndex], a[midIndex-1]) == 0){ //Keep going back one by one on the array.
                midIndex = midIndex-1;
            }
            indexPosition = midIndex; //Return the index of the lowest thing that matches.
        }
        /**
         * If the provided key is larger than the middle index, eliminate half the array and check the midpoint again.
         */
        else if (comparator.compare(key, a[midIndex]) == 1){
            low = midIndex + 1; // +1 because arrays start at zero.
            indexPosition = recursiveFirstIndexOf(a, key, comparator, low, high);
        }
        else if (comparator.compare(key, a[midIndex]) == -1){
            high = midIndex;
            indexPosition = recursiveFirstIndexOf(a, key, comparator, low, high);
        }
        return indexPosition; //Will return -1 (from the beginning) if nothing was found.
    }


    /**
     * Calls a recursive binary search function to find the index of the object. Verifies it exists
     * @param a
     * @param key
     * @param comparator
     * @param <Key>
     * @return
     * @throws NoSuchObjectException if the object is not found.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) throws NoSuchObjectException{
        System.out.println("BinarySearchDeluxe.lastIndexOf");
        int low = 0;
        int high = a.length;
        int indexToBeReturned;

        //Begin Recursive Call
        indexToBeReturned = lastIndexOfRecursive(a, key, comparator, low, high);

        //
        if (indexToBeReturned == -1){
            System.out.println("BinarySearchDeluxe.lastIndexOf: "); //TODO Delete Test Code
            throw new NoSuchObjectException("No Results, element does not exist.");
        }

        else{
            return indexToBeReturned;
        }
    }

    public static <Key> int lastIndexOfRecursive(Key[] a, Key key, Comparator<Key> comparator, int low, int high){
        int midIndex = low + ((high-low)/2);
        int indexPosition = -1; //Will be -1 if it doesn't exist.

        if (comparator.compare(key, a[midIndex]) == 0){ //If key is equal to midIndex TODO: Go find equal/earlier shit that also matches.
            return midIndex;
        }

        if (comparator.compare(key, a[midIndex]) == 1){ //Key is larger than the mid-index in the array.
            low = midIndex+1; //TODO: Consider removing +1. Josh thinks this won't work but Mike Chase does. I don't need to compare value twice? Once in midIndex - MGC
            lastIndexOfRecursive(a, key, comparator, low, high);
        }

        if (comparator.compare(key, a[midIndex]) == -1){ //Key is smaller than the mid-index
            high = midIndex-1; //TODO: Consider removing -1. Josh thinks this won't work. Mike Chase thinks it might speed it up (very slightly).
            lastIndexOfRecursive(a, key, comparator, low, high);
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
}
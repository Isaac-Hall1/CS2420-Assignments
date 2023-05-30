/**
 *
 * This class sorts a given arraylist of values using either a merge sort
 * or a quicksort
 *
 * @author Bradley Bartelt and Isaac Hall
 * @version 2/20/2023
 */
package assign05;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {
    public static int cutoff = 15;
    /**
     *
     * Driver method for merge sort, sets up helper arrayList and confirms that the array is valid
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr){
        ArrayList<T> helpArr = new ArrayList<T>();
        // fills helper arrayList
        for(T i: arr){
            helpArr.add(i);
        }
        // checks if array is valid
        if(arr.size() < 1)
            return;
        // starts the recursive method
        mergeSortArray(arr, helpArr, 0, arr.size()-1,cutoff);
    }

    /**
     *
     * recursive method for mergeSort which calls itself until it has devided the arrayList until its cutoff point
     * in which itll use insertion sort, at the end it merges the list together
     *
     * @param arr
     * @param helpArr
     * @param begin
     * @param end
     * @param cutoff
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void mergeSortArray(ArrayList<T> arr, ArrayList<T> helpArr, int begin, int end, int cutoff){
        if (begin < end){  // b: 0 E: 3 C: 2
            // if the length of the subarrays is less than the cutoff length, we run insertion sort
            if(end - begin <= cutoff -1){
                insertionSort(arr, begin, end);
                return;
            }
            int middle = begin + (end - begin)/2;  // 1
            // recursivly shorten the size of the subarrays until either reaching the cutoff or the begin Val
            // is less than the ending val
            mergeSortArray(arr,helpArr, begin, middle, cutoff);  // 0  1
            mergeSortArray(arr, helpArr,middle+1, end, cutoff); //  2 3
            // merges values all together
            merge(arr, helpArr, begin, middle, end);
        }
    }

    /**
     *
     * sorts and merges the subarrays into the final array
     *
     * @param arr
     * @param helpArr
     * @param begin
     * @param middle
     * @param end
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> helpArr, int begin, int middle, int end){
        //assigns values of helpArr from our original array from beginning to the end.
        for (int i = begin; i <= end; i++){
            helpArr.set(i,arr.get(i));
        }

        int i = begin;
        int j = middle + 1;
        int k = begin;
        //compares values from beginning and middle and increments along them
        while(i <= middle && j <= end){
            // if the left value is smaller or equal to the right value, we keep the begging value
            if(helpArr.get(i).compareTo(helpArr.get(j)) <=0) {
                arr.set(k, helpArr.get(i));
                i++;
            }
            // if the left value is larger, we swap the two value
            else{
                arr.set(k,helpArr.get(j));
                j++;
            }
            // every time the loop is run we move the k (beginning) value through the subarray
            k++;
        }
        // fills the correct values of the original array up until i passes the middle value
        while (i <= middle){
            arr.set(k,helpArr.get(i));
            k++;
            i++;
        }
    }

    /**
     * private method that implements insertion sort, so we can call it at a specified cutoff point when using mergesort
     * @param arr
     * @param begin
     * @param end
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int begin, int end){
        // 5 3 9 10 6
        T key;
        int j;

        for (int i = begin + 1; i <= end; i++) {
            //assigns key to the array at index i and then makes j one value less
            key = arr.get(i);
            j = i - 1;

            // runs a comparison between arr[j] and key while j less than or equal to begin
            while (j >= begin && arr.get(j).compareTo(key) >= 0) {
                // moves the largest value up the list (to the front, to index begin)
                arr.set(j + 1,arr.get(j));
                j = j - 1;
            }
            // replaces the value that was pasted over
            arr.set(j + 1,key);
        }
    }

    /**
     * generic type quicksort Algorithm driver method, calls recursive quicksort method.
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr){
        if(arr.size() < 1)
            return;
        quicksortArray(arr, 0, arr.size() -1);
    }

    /**
     * Implementation of quicksort. Recursively sorts through portions of the array by chossing a pivot point and partitioning
     * @param arr
     * @param begin
     * @param end
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void quicksortArray(ArrayList<T> arr, int begin, int end) {
        //if we have a subarray of size 1 exit the current call
        if(begin >= end)
            return;

        /*
        code for choosing a pivot value, first line uses the middle position as the pivot
        next uses the last value in the subarray as the pivot,
        and the third line uses a random value in the subarray as the pivot.
         */

        int pivot = ((end-begin)/2) + begin;
        //int pivot = end;
        //int pivot = ((int)Math.random()*(end-begin)) + begin;


        //moves the pivot to the end of the subarray
        swapValues(arr, pivot, end);
        int leftSide = begin;
        int rightSide = end;

        //while both pointers are still on their corresponding sides
        while(leftSide < rightSide){
            //increment the left pointer until we find a value that needs to be swapped
            while(arr.get(leftSide).compareTo(arr.get(end)) <= 0 && leftSide < rightSide){
                leftSide++;
            }
            //increment the right pointer until we find a value that needs to be swapped
            while(arr.get(rightSide).compareTo(arr.get(end)) >=0 && leftSide < rightSide){
                rightSide--;
            }
            //swap the values
            swapValues(arr,leftSide,rightSide);
        }
        //place the pivot into the right position
        swapValues(arr,leftSide, end);

        //recursive calls to sort subarrays
        quicksortArray(arr, begin, leftSide -1);
        quicksortArray(arr,leftSide +1, end);
    }

    /**
     * private method used to swap the values of the two array indexes provided
     * @param arr
     * @param index1
     * @param index2
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void swapValues(ArrayList<T> arr,int index1, int index2){
        T temp = arr.get(index1);
        arr.set(index1,arr.get(index2));
        arr.set(index2,temp);


    }

    /**
     * generates an array of ascending integers from 1 to the specified size
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateAscending(int size){
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for(int i = 1; i <= size; i++){
            intList.add(i);
        }
        return intList;
    }

    /**
     * generates an array containing randomly placed integers from 1 to specified size
     * @param size
     * @return
     */
    public static ArrayList<Integer> generatePermuted(int size){
        ArrayList<Integer> intList = generateAscending(size);
        Collections.shuffle(intList);
        return intList;
    }

    /**
     * generates an array of descending integers from size to 1
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateDescending(int size){
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for(int i = size; i > 0; i--){
            intList.add(i);
        }
        return intList;
    }

}

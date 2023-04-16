package assign04;

import java.io.FileNotFoundException;
import java.util.*;
import java.math.BigInteger;
import java.io.File;

public class LargestNumberSolver<T> {
    /**
     *
     * insertionSort() sorts the given array in terms of the given comparator method
     * the method sorts it so that if all numbers were pushed together, it would create
     * the biggest number possible with the given numbers in the array.
     *
     * @param arr
     * @param cmp
     *
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp){

        T key;
        int j;

        for (int i = 1; i < arr.length; i++) {
            //assigns key to the array at index i and then makes j one value less
            key = arr[i];
            j = i - 1;

            // runs a comparison between arr[j] and key while j less than or equal to 0
            while (j >= 0 && cmp.compare(arr[j] , key) > 0) {
                // moves the largest value up the list (to the front, to index 0)
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            // replaces the value that was pasted over
            arr[j + 1] = key;
        }
    }

    /**
     * Converts contents of Array to bigInteger
     *
     * @param arr
     * @return BigInteger
     */
    public static BigInteger findLargestNumber(Integer[] arr){
        insertionSort(arr, new largestNumberComparator());
        StringBuilder bigNumber = new StringBuilder();
        if(arr.length == 0){
            return new BigInteger("0");
        }
        //appends each integer in the array to a string to use in the constructor for BigInt
        for(int i = 0; i < arr.length; i++){
            bigNumber.append(arr[i]);
        }
        return new BigInteger(bigNumber.toString());
    }

    /**
     * converts the contents of the given array into an integer. If the contents are too large to store as an int,
     * an exception is thrown.
     *
     * @param arr
     * @return int
     * @throws OutOfRangeException
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException{
        BigInteger val = findLargestNumber(arr);
        BigInteger maxInt = BigInteger.valueOf(Integer.MAX_VALUE);
        //if the number is too large to be an int, exception is thrown
        if(val.compareTo(maxInt) > 0){
            throw new OutOfRangeException("Array contains a number too large to be stored as an integer");
        }
        return val.intValue();
    }

    /**
     *
     * Checks if the largestBigInteger value form a list can be converted to a long without throwing an error
     * returns the long value of the BigInteger value
     *
     * @param arr
     * @return long
     * @throws OutOfRangeException
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException{
        BigInteger val = findLargestNumber(arr);
        BigInteger maxInt = BigInteger.valueOf(Long.MAX_VALUE);
        //if the number is too large to be a long, exception is thrown
        if(val.compareTo(maxInt) > 0){
            throw new OutOfRangeException("Array contains a number too large to be stored as a long");
        }
        return val.longValue();
    }

    /**
     * sums the BigIntegerValue of every array given from the list
     *
     * @param list
     * @return BigInteger
     */
    public static BigInteger sum(List<Integer[]> list){
        BigInteger total = new BigInteger("0");

        for(int i= 0; i <list.size();i++){

            Integer[] temp = new Integer[list.get(i).length];

            //copy the array over to avoid changing the original
            for(int j = 0; j < list.get(i).length; j++) {
                temp[j] = list.get(i)[j];
            }
            total = total.add(findLargestNumber(temp));
        }
        return total;
    }

    /**
     * Given a list of arrays and an integer that specifies the kth largest array
     * returns the kth largest array.
     *
     * @param list
     * @param k
     * @return Array
     * @throws IllegalArgumentException
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
        if(k >= list.size() || k < 0)
            throw new IllegalArgumentException("K is outside the range of the given list");

        //creating array to store bigInt values of all the arrays in the list,
        //as well as a list to store the original positions of those values before sorting
        BigInteger[] bigIntArraySorted = new BigInteger[list.size()];
        List<BigInteger> bigIntArrayOriginalPosition = new ArrayList<BigInteger>();


        //populate the array and list
        for(int i = 0; i < list.size();i++){

            Integer[] temp = new Integer[list.get(i).length];

            //copy the array over to avoid changing the original
            for(int j = 0; j < list.get(i).length; j++) {
                temp[j] = list.get(i)[j];
            }

            bigIntArraySorted[i] = findLargestNumber(temp);
            bigIntArrayOriginalPosition.add(findLargestNumber(temp));
        }

        //sort the bigInt array
        insertionSort(bigIntArraySorted, new BigIntComparator());

        //collects the BigInt value at the specified kth position, then returns its corresponding array
        int val = bigIntArrayOriginalPosition.indexOf(bigIntArraySorted[k]);
        return list.get(val);

    }

    /**
     * Reads a file of integers a
     * @param filename of file location and name
     * @return List of Integer arrays
     */
    public static List<Integer[]> readFile(String filename){

        try(Scanner read = new Scanner(new File (filename));){

            List list = new ArrayList<Integer[]>();

            while(read.hasNextLine()){
                //stores the entire current line as a string
                String currentArray = read.nextLine();
                int size = 0;
                Scanner stringReader = new Scanner(currentArray);

                //finds how many seperate numeric values are in the current line
                while(stringReader.hasNext()){
                    stringReader.nextInt();
                    size++;
                }

                Integer[] tempArray = new Integer[size];

                stringReader = new Scanner(currentArray);

                //adds each numeric value from the current line into an integer array
                for(int i = 0; i < size; i++){
                    int tempInt = stringReader.nextInt();
                    tempArray[i] = tempInt;
                }
                //adds the Integer array storing all the values for the current line into the list
                list.add(tempArray);
            }
            return list;
        }
        catch (FileNotFoundException e){
        }
        List<Integer[]> emptyList = new ArrayList<Integer[]>();
        return emptyList;
    }

    /**
     * comparator used to compare integers to order them properly for largestNumberSolver
     */
    public static class largestNumberComparator implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {

            //uses stringbuilder to combine the two entered numbers, once with the o1 coming first
            StringBuilder xy = new StringBuilder();
            xy.append(o1.toString());
            xy.append(o2.toString());

            //and another where o2 comes first
            StringBuilder yx = new StringBuilder();
            yx.append(o2.toString());
            yx.append(o1.toString());

            //compares the two created values, returning 1 if xy is larger, -1 if yx is larger, and 0 if they're equal
            return -(new BigInteger(String.valueOf(xy)).compareTo(new BigInteger(String.valueOf(yx))));
        }
    }

    public static class BigIntComparator implements Comparator{
        @Override
        //uses BigIntegers compareTo method to compare, returning 1,-1, or 0
        public int compare(Object o1, Object o2) {
            return -((BigInteger)o1).compareTo((BigInteger)o2);
        }
    }


}


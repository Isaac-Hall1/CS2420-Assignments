package assign04;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LargestNumberSolverTester {
    @BeforeEach
    void setUp(){

    }

    @Test
    public void testInsertionSortIntegers(){

        Integer[] arr = new Integer[]{4,7,3,6,8,9};
        LargestNumberSolver.insertionSort(arr,new LargestNumberSolver.largestNumberComparator());

        assertEquals(9,arr[0]);
        assertEquals(8,arr[1]);
        assertEquals(7,arr[2]);
        assertEquals(6,arr[3]);
        assertEquals(4,arr[4]);
        assertEquals(3,arr[5]);

    }
    @Test
    public void testInsertionSortLongs(){
        Long[] longArray = new Long[]{4l,7l,3l,6l,8l,9l};
        LargestNumberSolver.insertionSort(longArray, new LargestNumberSolver.largestNumberComparator());
        assertEquals(9l,longArray[0]);
        assertEquals(8l,longArray[1]);
        assertEquals(7l,longArray[2]);
        assertEquals(6l,longArray[3]);
        assertEquals(4l,longArray[4]);
        assertEquals(3l,longArray[5]);
    }

    @Test
    public void testInsertionSortWithLargeNumberComparator(){
        Integer[] arr = new Integer[]{11, 67, 79, 7, 22, 13};
        LargestNumberSolver.insertionSort(arr, new LargestNumberSolver.largestNumberComparator());

        assertEquals(arr[0], 79);
        assertEquals(arr[1], 7);
        assertEquals(arr[2], 67);
        assertEquals(arr[3], 22);
        assertEquals(arr[4], 13);
        assertEquals(arr[5], 11);
    }
    @Test
    public void testEmptyArrayInsertionSort(){
       Integer[] arr = new Integer[]{};
       LargestNumberSolver.insertionSort(arr,new LargestNumberSolver.largestNumberComparator());
    }


    @Test
    public void testFindLargestNumber(){
        Integer[] arr = new Integer[]{11, 67, 79, 7, 22, 13};
        BigInteger real = BigInteger.valueOf(79767221311l);
        assertTrue(real.equals(LargestNumberSolver.findLargestNumber(arr)));
    }
    @Test
    public void testFindLargestNumberEmpty(){
        Integer[] arr = new Integer[]{};
        BigInteger real = new BigInteger("0");
        assertTrue(real.equals(LargestNumberSolver.findLargestNumber(arr)));
    }
    @Test
    public void testFindLargestInt(){
        Integer[] arr = new Integer[]{11, 67, 79, 7};
        int test = LargestNumberSolver.findLargestInt(arr);
        int real = 7976711;
        assertEquals(real,test);
    }
    @Test
    public void testFindLargestIntEmpty(){
        Integer[] arr = new Integer[]{};
        BigInteger real = new BigInteger("0");
        assertTrue(real.intValue() == (LargestNumberSolver.findLargestInt(arr)));
    }
    @Test
    public void testFindLargestIntThrows(){
        Integer[] arr = new Integer[]{11, 67, 79, 7, 22, 13};
        assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(arr);});
    }
    @Test
    public void testFindLargestLong(){
        Integer[] arr = new Integer[]{11, 67, 79, 7, 22, 13};
        long test = LargestNumberSolver.findLargestLong(arr);
        long real = 79767221311l;
        assertEquals(real,test);
    }
    @Test
    public void testFindLargestLongThrows(){
        Integer[] arr = new Integer[]{11, 67, 79, 79, 22, 13, 99, 11, 11, 111, 11, 11,11,11,11,11,11,11,11,11};
        assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestLong(arr);});
    }
    @Test
    public void testFindLargestLongEmpty(){
        Integer[] arr = new Integer[]{};
        BigInteger real = new BigInteger("0");
        assertTrue(real.longValue() == (LargestNumberSolver.findLargestLong(arr)));
    }
    @Test
    public void testFindLargestLongIsAsBigAsLongMAX_VALUE(){
        Integer[] arr = new Integer[]{922337203,685477,5807};
        assertEquals(9223372036854775807l,LargestNumberSolver.findLargestLong(arr));
    }

    @Test
    public void testReadFile(){
        List list = LargestNumberSolver.readFile("integers.txt");
    }
    @Test
    public void testReadFileIncorrectFileName(){
        List list = LargestNumberSolver.readFile("WrongName.txt");
        assertEquals(0,list.size());
    }
    @Test
    public void testSum(){
        List list = new ArrayList<Integer[]>();
        list.add(new Integer[]{1,1,1,1});
        list.add(new Integer[]{1,1,1,1});
        list.add(new Integer[]{1,1,1,1});
        BigInteger real = new BigInteger("3333");
        assertEquals(real, LargestNumberSolver.sum(list));
    }
    @Test
    public void testSumDiffSizedArrays() {
        List list = new ArrayList<Integer[]>();
        list.add(new Integer[]{1, 1, 1, 1,2});
        list.add(new Integer[]{1, 1, 1, 1});
        list.add(new Integer[]{1, 1, 1, 1,3,4});
        BigInteger real = new BigInteger("453333");
        assertEquals(real, LargestNumberSolver.sum(list));
    }
    @Test
    public void testSumEmpty(){
        List list = new ArrayList<Integer[]>();
        BigInteger real = new BigInteger("0");
        assertEquals(real, LargestNumberSolver.sum(list));
    }

    @Test
    public void testFindKthLargest(){
        List list = new ArrayList<Integer[]>();
        list.add(new Integer[]{1,1,1});
        list.add(new Integer[]{4,4,4,4});
        list.add(new Integer[]{3,3,3,3,5});
        list.add(new Integer[]{9,9,9,9});
        list.add(new Integer[]{2,2,2,2,99});
        list.add(new Integer[]{3,377,3,3,8});

        assertEquals(list.get(5),LargestNumberSolver.findKthLargest(list,0));
        assertEquals(list.get(2),LargestNumberSolver.findKthLargest(list,2));
        assertEquals(list.get(0),LargestNumberSolver.findKthLargest(list,5));
    }
    @Test
    public void testFindKthLargestThrowsIllegalArgumentException(){
        List list = new ArrayList<Integer[]>();
        list.add(new Integer[]{1,1,1});
        list.add(new Integer[]{4,4,4,4});
        list.add(new Integer[]{3,3,3,3,5});
        list.add(new Integer[]{9,9,9,9});
        list.add(new Integer[]{2,2,2,2,99});
        list.add(new Integer[]{3,377,3,3,8});

        assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(list,6);});
        assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(list,-1);});

    }

    @Test
    public void testReadFileWithLargeFile(){
        List<Integer[]> testList = LargestNumberSolver.readFile("integers.txt");

        assertEquals(16,(testList.get(180))[0]);
        assertEquals(102,(testList.get(180))[1]);
        assertEquals(85,(testList.get(902))[0]);
        assertEquals(410,(testList.get(0))[0]);
    }

    @Test
    public void testBenProvidedTest(){
        Integer[] temp = new Integer[]{999, 639, 1, 7, 58, 9};
        assertEquals(new BigInteger("99997639581"),LargestNumberSolver.findLargestNumber(temp));
    }
    @Test
    public void testBenProvidedKthTest(){
        Integer[] temp = new Integer[]{88,51};
        Integer[] temp1 = new Integer[]{7,42,97};
        List list = new ArrayList<Integer[]>();
        list.add(temp);
        list.add(temp1);
        assertEquals(7,LargestNumberSolver.findKthLargest(list,0)[0]);
    }



}

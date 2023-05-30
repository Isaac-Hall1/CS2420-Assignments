package assign05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListSorterTest {
    ArrayList<Integer> smallList;
    @BeforeEach
    void setUp(){

    }
    @Test
    public void testGenerateAscendingEmpty(){
        ArrayList<Integer> test = ArrayListSorter.generateAscending(0);
        assertEquals(0, test.size());
    }
    @Test
    public void testGenerateDescendingEmpty(){
        ArrayList<Integer> test = ArrayListSorter.generateDescending(0);
        assertEquals(0, test.size());
    }
    @Test
    public void testGeneratePermutedEmpty(){
        ArrayList<Integer> test = ArrayListSorter.generatePermuted(0);
        assertEquals(0, test.size());
    }
    @Test
    public void testGenerateAscending(){
        ArrayList<Integer> test = ArrayListSorter.generateAscending(5);
        assertEquals(1,test.get(0));
        assertEquals(2,test.get(1));
        assertEquals(3,test.get(2));
        assertEquals(4,test.get(3));
        assertEquals(5,test.get(4));
    }

    @Test
    public void testGenerateDescending(){
        ArrayList<Integer> test = ArrayListSorter.generateDescending(5);
        assertEquals(1,test.get(4));
        assertEquals(2,test.get(3));
        assertEquals(3,test.get(2));
        assertEquals(4,test.get(1));
        assertEquals(5,test.get(0));
    }

    @Test
    public void testGeneratePermuted(){
        ArrayList<Integer> test = ArrayListSorter.generatePermuted(30);
        for(int i = 0; i < 30; i++){
            assertTrue(test.contains(i+1));
        }

    }


    @Test
    public void testMergeSort(){
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(7);
        test.add(4);
        test.add(2);
        test.add(8);
        test.add(9);
        test.add(3);
        test.add(14);
        test.add(5);

        ArrayListSorter.mergesort(test);

        assertEquals(2,test.get(0));
        assertEquals(3,test.get(1));
        assertEquals(14, test.get(7));
    }

    @Test
    public void testMergeSortBigArray() {
        ArrayList<Integer> test = ArrayListSorter.generatePermuted(100);

        ArrayListSorter.mergesort(test);

        for (int i = 1; i<=100; i++){
            assertEquals(i,test.get(i - 1));
        }
    }

    @Test
    public void testquicksort(){
        ArrayList<Integer> test = ArrayListSorter.generatePermuted(30);
        ArrayListSorter.quicksort(test);

        assertEquals(1,test.get(0));
        assertEquals(8,test.get(7));
        assertEquals(30, test.get(29));
        assertEquals(5, test.get(4));
    }

    @Test
    public void testQuickSort(){
        ArrayList<Integer> test = ArrayListSorter.generatePermuted(100);
        ArrayListSorter.quicksort(test);

        for (int i = 1; i<=100; i++){
            assertEquals(i,test.get(i - 1));
        }
    }
    @Test
    public void testQuickSortDescending(){
        ArrayList<Integer> test = ArrayListSorter.generateDescending(100);
        ArrayListSorter.quicksort(test);

        for (int i = 1; i<=100; i++){
            assertEquals(i,test.get(i - 1));
        }
    }
    @Test
    public void testQuickSortOnSortedArray(){
        ArrayList<Integer> test = ArrayListSorter.generateAscending(100);
        ArrayListSorter.quicksort(test);
        for (int i = 1; i<=100; i++){
            assertEquals(i,test.get(i - 1));
        }
    }

    @Test
    public void testMergesortSortedArray(){
        ArrayList<Integer> test = ArrayListSorter.generateAscending(100);
        ArrayListSorter.mergesort(test);

        for (int i = 1; i<=100; i++){
            assertEquals(i,test.get(i - 1));
        }
    }

    @Test
    public void testMergesortDescendingArray(){
        ArrayList<Integer> test = ArrayListSorter.generateDescending(100);
        ArrayListSorter.mergesort(test);

        for (int i = 1; i<=100; i++){
            assertEquals(i,test.get(i - 1));
        }
    }
}

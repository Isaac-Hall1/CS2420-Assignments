package assign10;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester class for testing the methods in the FindKthLargest class
 */
public class KthTester {
    private FindKLargest kLargest;
    private List<Integer> list;

    @BeforeEach
    void setUp(){
        list = new ArrayList<>();
        list.add(5);
        list.add(8);
        list.add(2);
        list.add(1);
        list.add(9);
        list.add(0);
    }
    @Test
    void kthLargestHeapComparable(){
        List<Integer> returnList = FindKLargest.findKLargestHeap(list,3);
        assertEquals(9,returnList.get(0));
        assertEquals(8,returnList.get(1));
        assertEquals(5,returnList.get(2));
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestHeap(list,7);});
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestHeap(list,-7);});

    }

    @Test
    void kthLargestHeapComparator(){
        List<Integer> returnList = FindKLargest.findKLargestHeap(list,3,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertEquals(9,returnList.get(0));
        assertEquals(8,returnList.get(1));
        assertEquals(5,returnList.get(2));
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestHeap(list,7);});
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestHeap(list,-7);});
    }
    @Test
    void kthLargestHeapComparableSort(){
        List<Integer> returnList = FindKLargest.findKLargestSort(list,3);
        assertEquals(9,returnList.get(0));
        assertEquals(8,returnList.get(1));
        assertEquals(5,returnList.get(2));
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestSort(list,7);});
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestSort(list,-7);});
    }
    @Test
    void kthLargestHeapComparatorSort(){
        List<Integer> returnList = FindKLargest.findKLargestSort(list,3,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertEquals(9,returnList.get(0));
        assertEquals(8,returnList.get(1));
        assertEquals(5,returnList.get(2));
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestSort(list,7);});
        assertThrows(IllegalArgumentException.class, () -> {FindKLargest.findKLargestSort(list,-1);});
    }
}

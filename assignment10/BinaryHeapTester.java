package assign10;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester class for testing the methods in the BinaryMaxHeap class
 */
public class BinaryHeapTester {
    private BinaryMaxHeap<Integer> heap;
    private List<Integer> list;
    @BeforeEach
    void setUp(){
        list = new ArrayList();
        list.add(5);
        list.add(8);
        list.add(2);
        list.add(1);
        list.add(9);
        list.add(0);

    }
    @Test
    void heapConstructorTestComparableList() {

        heap = new BinaryMaxHeap<>(list);
        assertEquals(6,heap.size());
        assertEquals(9, heap.extractMax());
        assertEquals(8, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(2, heap.extractMax());
        assertEquals(1, heap.extractMax());
        assertEquals(0, heap.extractMax());

    }
    @Test
    void heapConstuctorTestCompatorList(){
        heap = new BinaryMaxHeap<>(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertEquals(9, heap.extractMax());
        assertEquals(8, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(2, heap.extractMax());
        assertEquals(1, heap.extractMax());
        assertEquals(0, heap.extractMax());
    }
    @Test
    void heapConstructorTestComparatorNoList(){
        heap = new BinaryMaxHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        heap.add(5);
        heap.add(8);
        heap.add(2);
        heap.add(1);
        heap.add(9);
        heap.add(0);
        assertEquals(9, heap.extractMax());
        assertEquals(8, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(2, heap.extractMax());
        assertEquals(1, heap.extractMax());
        assertEquals(0, heap.extractMax());

    }

    @Test
    void heapConstructorTestComparator(){
        heap = new BinaryMaxHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        heap.add(5);
        heap.add(8);
        heap.add(2);
        heap.add(1);
        heap.add(9);
        heap.add(0);
        assertEquals(9, heap.extractMax());
        assertEquals(8, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(2, heap.extractMax());
        assertEquals(1, heap.extractMax());
        assertEquals(0, heap.extractMax());
    }
    @Test
    void addTest(){
        heap = new BinaryMaxHeap<>();
        heap.add(9);
        assertEquals(9,heap.peek());
        heap.add(120);
        heap.add(6);
        heap.add(9);
        assertEquals(120,heap.peek());
        heap.add(150);
        assertEquals(150,heap.peek());
    }
    @Test
    void extractMax(){
        heap = new BinaryMaxHeap<>(list);
        assertEquals(9,heap.extractMax());
        heap.add(100);
        assertEquals(100,heap.extractMax());
        heap.clear();
        assertThrows(NoSuchElementException.class, () -> {heap.extractMax();});

    }
    @Test
    void clearTest(){
        heap = new BinaryMaxHeap<>(list);
        heap.clear();
        assertEquals(0,heap.size());
        assertThrows(NoSuchElementException.class, () -> {heap.peek();});
        heap.add(10);
        assertEquals(10, heap.peek());
    }
    @Test
    void isEmptyTest(){
        heap = new BinaryMaxHeap<>();
        assertTrue(heap.isEmpty());
        heap.add(9);
        assertFalse(heap.isEmpty());
        heap.extractMax();
        assertTrue(heap.isEmpty());
    }
    @Test
    void sizeTest(){
        heap = new BinaryMaxHeap<>();
        assertEquals(0,heap.size());
        heap.add(9);
        heap.add(8);
        heap.add(3);
        assertEquals(3,heap.size());
        heap.extractMax();
        assertEquals(2,heap.size());
    }
    @Test
    void toArrayTest(){
        heap = new BinaryMaxHeap<>(list);
        assertEquals(9, heap.toArray()[0]);
        assertEquals(8,heap.toArray()[1]);
        assertEquals(2,heap.toArray()[2]);
        assertEquals(1,heap.toArray()[3]);
        assertEquals(5,heap.toArray()[4]);
        assertEquals(0,heap.toArray()[5]);
    }
    @Test
    void peekTest(){
        heap = new BinaryMaxHeap<>();
        assertThrows(NoSuchElementException.class, () -> {heap.peek();});
        heap.add(100);
        assertEquals(100,heap.peek());
        heap.add(2);
        heap.add(1000);
        heap.add(999);
        heap.add(80);
        assertEquals(1000,heap.peek());
        heap.extractMax();
        heap.extractMax();
        assertEquals(100,heap.peek());
        heap.extractMax();
        heap.extractMax();
        heap.extractMax();
        assertThrows(NoSuchElementException.class, () -> {heap.peek();});
        heap.add(999);
        heap.clear();
        assertThrows(NoSuchElementException.class, () -> {heap.peek();});
    }

    @Test
    void negativeTest(){
        list.clear();

        list.add(-10);
        list.add(-123291);
        list.add(-8);
        heap = new BinaryMaxHeap<>(list);

    }
    @Test
    void percolateDown(){
        list.clear();
        list.add(80);
        list.add(-8);
        list.add(77);
        list.add(0);
        list.add(55);

        heap = new BinaryMaxHeap<>(list);
        assertEquals(80,heap.peek());
    }

}

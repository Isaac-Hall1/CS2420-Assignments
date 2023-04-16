package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
public class SinglyLinkedListTester {
    private SinglyLinkedList<Integer> testList;
    private Iterator smallIterator;
    @BeforeEach
    public void setup(){

        testList = new SinglyLinkedList<Integer>();
        testList.insertFirst(1);
        testList.insertFirst(2);
        testList.insertFirst(3);
        testList.insertFirst(4);
        testList.insertFirst(5);
        smallIterator = testList.iterator();
    }
    @Test
    public void insertFirst(){
        var test = new SinglyLinkedList<Integer>();
        test.insertFirst(1);
        test.insertFirst(2);
        assertEquals(2, test.getFirst());
    }
    @Test
    public void insertTest(){
        //testList.insert(2,9);
        testList.insert(5,10);
        //assertEquals(9,testList.get(2));
        assertEquals(10,testList.get(5));
    }
    @Test
    public void getFirst(){
        assertEquals(5,testList.getFirst());
    }
    @Test
    public void get(){
        assertEquals(4,testList.get(1));
        assertEquals(1,testList.get(4));
    }
    @Test
    public void deleteFirst(){
        testList.deleteFirst();
        assertEquals(4,testList.get(0));
    }
    @Test
    public void delete(){
        testList.delete(2);
        assertEquals(2,testList.get(2));
    }
    @Test
    public void indexOf(){
        int index = testList.indexOf(3);
        assertEquals(2,index);
    }
    @Test
    public void sizeTest(){
        int size = testList.size();
        assertEquals(5,size);
        testList.insertFirst(9);
        size = testList.size();
        assertEquals(6,size);
    }
    @Test
    public void isEmpty(){
        var test = new SinglyLinkedList<Integer>();
        assertTrue(test.isEmpty());
        test.insertFirst(1);
        assertFalse(test.isEmpty());
    }
    @Test
    public void clear(){
        testList.clear();
        assertTrue(testList.isEmpty());
    }
    @Test
    public void toArray(){
        Object[] test = testList.toArray();
        assertEquals(5,test[0]);
        assertEquals(4,test[1]);
        assertEquals(3,test[2]);
        assertEquals(2,test[3]);
        assertEquals(1,test[4]);

    }
    @Test
    public void IteratorHasNextTest(){
        for(int i =0; i< 5; i++) {
            assertTrue(smallIterator.hasNext());
            smallIterator.next();
        }
        assertFalse(smallIterator.hasNext());
    }
    @Test
    public void IteratorNextTest(){
        for(int i =5; i > 0; i--) {
            assertEquals(i,smallIterator.next());
        }
    }
    @Test
    public void IteratorRemoveTest(){
        smallIterator.next();
        smallIterator.next();
        smallIterator.remove();
        assertEquals(5,testList.get(0));
        assertEquals(3,testList.get(1));
        assertEquals(2,testList.get(2));
        assertEquals(1,testList.get(3));
    }
    @Test
    public void IteratorRemoveFirstValue(){
        smallIterator.next();
        smallIterator.remove();
        assertEquals(4,testList.get(0));
        assertEquals(3,testList.get(1));
        assertEquals(2,testList.get(2));
        assertEquals(1,testList.get(3));
    }
    @Test
    public void IteratorRemoveTwice(){
        smallIterator.next();
        smallIterator.remove();
        smallIterator.next();
        smallIterator.remove();
        assertEquals(3,testList.get(0));
        assertEquals(2,testList.get(1));
        assertEquals(1,testList.get(2));

    }
    @Test
    public void InsertThrowsError(){
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.insert(8,8);});
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.insert(-2,8);});
        testList.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.insert(0,8);});
    }

    @Test
    public void GetFirstThrows(){
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> {test.getFirst();});
    }

    @Test
    public void GetThrowsError(){
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.get(8);});
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.get(-2);});
    }

    @Test
    public void DeleteFirstThrows(){
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> {test.deleteFirst();});
    }

    @Test
    public void DeleteThrows(){
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.delete(8);});
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.delete(-2);});
        testList.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.delete(0);});
    }
    @Test
    public void toArrayOnEmptyLinkedList(){
        testList.clear();
        assertEquals(0,testList.toArray().length);
    }

}

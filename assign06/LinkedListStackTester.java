package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
public class LinkedListStackTester {
    private LinkedListStack testList;
    @BeforeEach
    public void setUp(){
        testList = new LinkedListStack();
        testList.push(1);
        testList.push(2);
        testList.push(3);
        testList.push(4);
        testList.push(5);
    }
    @Test
    public void clearTest(){
        testList.clear();
        assertEquals(0,testList.size());
    }
    @Test
    public void isEmptyTest(){
        testList.clear();
        assertTrue(testList.isEmpty());
    }
    @Test
    public void peekTest(){
        assertEquals(5,testList.peek());
    }
    @Test
    public void popTest(){
        assertEquals(5,testList.pop());
        assertEquals(4,testList.size());
    }
    @Test
    public void pushTest(){
        testList.push(6);
        assertEquals(6,testList.peek());
    }
    @Test
    public void sizeTest(){
        assertEquals(5,testList.size());
        testList.push(7);
        testList.push(8);
        assertEquals(7,testList.size());
        testList.clear();
        assertEquals(0,testList.size());
    }
    @Test
    public void isEmptyNonEmpty(){
        assertFalse(testList.isEmpty());
    }
    @Test
    public void clearOnEmptyList(){
        testList.clear();
        assertEquals(0,testList.size());
    }
    @Test
    public void peekThrows(){
        testList.clear();
        assertThrows(NoSuchElementException.class, () -> {testList.peek();});

    }
    @Test
    public void popThrows(){
        testList.clear();
        assertThrows(NoSuchElementException.class, () -> {testList.pop();});

    }
    @Test
    public void popALotTest(){
        testList.pop();
        testList.pop();
        testList.pop();
        assertEquals(2,testList.size());
    }
    @Test
    public void peekThenPopThenPeek(){
        assertEquals(5,testList.peek());
        testList.pop();
        assertEquals(4,testList.peek());
    }
    @Test
    public void pushPopPeek(){
        testList.push(7);
        testList.pop();
        assertEquals(5,testList.peek());
    }
    @Test
    public void popTheSizeOfListThenTestIsEmpty(){
        for(int i = 0; i < 5; i++){
            testList.pop();
        }
        assertTrue(testList.isEmpty());
    }
    @Test
    public void popMostOfListThenTestPeek(){
        for(int i = 0; i < 4; i++){
            testList.pop();
        }
        assertEquals(1, testList.peek());
    }
    @Test
    public void doesPushAndPopChangeSize(){
        assertEquals(5,testList.size());
        testList.pop();
        assertEquals(4,testList.size());
        testList.push(1);
        testList.push(1);
        assertEquals(6,testList.size());
    }

}

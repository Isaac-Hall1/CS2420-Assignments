package assign03;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SimplePriorityQueueTest {
    private UofUStudent a,b,c,d, e, f, g, h;
    private Collection collection;

    private SimplePriorityQueue<UofUStudent> studentList = new SimplePriorityQueue<UofUStudent>(new Comparator<UofUStudent>() {
        @Override
        public int compare(UofUStudent name1, UofUStudent name2) {

            if((name1.getLastName()).compareTo(name2.getLastName()) == 0){
                if((name1.getFirstName()).compareTo(name2.getFirstName()) == 0)
                    return name1.getUNID() - name2.getUNID();
                else
                    return name1.getFirstName().compareTo(name2.getFirstName());
            }
            else
                return name1.getLastName().compareTo(name2.getLastName());
        }
    });

    private SimplePriorityQueue<UofUStudent> simpleStudentList = new SimplePriorityQueue<UofUStudent>(new Comparator<UofUStudent>() {
        @Override
        public int compare(UofUStudent name1, UofUStudent name2) {
            if((name1.getLastName()).compareTo(name2.getLastName()) == 0){
                if((name1.getFirstName()).compareTo(name2.getFirstName()) == 0)
                    return name1.getUNID() - name2.getUNID();
                else
                    return name1.getFirstName().compareTo(name2.getFirstName());
            }
            else
                return name1.getLastName().compareTo(name2.getLastName());
        }
    });

    @BeforeEach
    void setUp() {
        collection = new ArrayList<UofUStudent>();
        //StudentList = new SimplePriorityQueue<UofUStudent>();
        a = new UofUStudent("aaa","aaa", 1000000);
        b = new UofUStudent("bbb","bbb",8008513);
        c = new UofUStudent("ccc","ccc",9999999);
        d = new UofUStudent("ddd", "ddd",1111111);
        e = new UofUStudent("eee", "eee",1111110);
        f = new UofUStudent("fff", "fff",1111111);
        g = new UofUStudent("ggg", "ggg",1111111);
        h = new UofUStudent("hhh", "hhh",9991111);

        simpleStudentList.insert(a);
        simpleStudentList.insert(b);
        simpleStudentList.insert(c);
        simpleStudentList.insert(d);
        simpleStudentList.insert(e);
        simpleStudentList.insert(f);
        simpleStudentList.insert(g);

    }
    // emptyArray tests ---------------------------------------------
    @Test
    public void testContainsOnEmptyArray(){
        assertFalse(studentList.contains(a));
    }
    @Test
    public void testFindMaxEmpryArray(){
        assertThrows(NoSuchElementException.class, () -> {studentList.findMax();});
    }
    @Test
    public void testDeleteMaxEmpryArray(){
        assertThrows(NoSuchElementException.class, () -> {studentList.deleteMax();});
    }
    @Test
    public void testSizeOnEmptyArray(){
        assertEquals(0,studentList.size());
    }
    // Insert Tests -------------------------------------------------
    @Test
    //tests if the insert method will place values in an array
    public void testInsertInsertStudents(){
        studentList.insert(a);
        studentList.insert(b);
        studentList.insert(c);
        studentList.insert(d);

        assertTrue(studentList.contains(a));
        assertTrue(studentList.contains(b));
        assertTrue(studentList.contains(c));
        assertTrue(studentList.contains(d));
    }
    @Test
    public void testInsertSort(){
        studentList.insert(h);
        studentList.insert(f);
        studentList.insert(b);
        studentList.insert(e);
        studentList.insert(d);

        assertEquals(studentList.getObjectAtPosition(0), h);
        assertEquals(studentList.getObjectAtPosition(1), f);
        assertEquals(studentList.getObjectAtPosition(2), e);
        assertEquals(studentList.getObjectAtPosition(3), d);
        assertEquals(studentList.getObjectAtPosition(4), b);
    }
    @Test
    public void testInsertAtBeginning(){
        studentList.insert(a);
        studentList.insert(f);
        studentList.insert(b);
        studentList.insert(e);
        studentList.insert(d);

        studentList.insert(h);

        assertEquals(studentList.getObjectAtPosition(0), h);
        assertEquals(studentList.getObjectAtPosition(1), f);
        assertEquals(studentList.getObjectAtPosition(2), e);
        assertEquals(studentList.getObjectAtPosition(3), d);
        assertEquals(studentList.getObjectAtPosition(4), b);
        assertEquals(studentList.getObjectAtPosition(5), a);
    }
    @Test
    public void testInsertDuplicates() {
        studentList.insert(a);
        studentList.insert(g);
        studentList.insert(g);
        studentList.insert(h);

        assertEquals(studentList.getObjectAtPosition(0), h);
        assertEquals(studentList.getObjectAtPosition(1), g);
        assertEquals(studentList.getObjectAtPosition(2), g);
        assertEquals(studentList.getObjectAtPosition(3), a);
    }
    @Test
    public void testInsertAllCollectionOfStudents(){
        collection.add(c);
        collection.add(b);
        collection.add(a);
        collection.add(d);

        studentList.insertAll(collection);

        assertEquals(studentList.getObjectAtPosition(0), d);
        assertEquals(studentList.getObjectAtPosition(1), c);
        assertEquals(studentList.getObjectAtPosition(2), b);
        assertEquals(studentList.getObjectAtPosition(3), a);
    }
    @Test
    public void testInsertGrowingArray(){
        collection.add(c);
        collection.add(b);
        collection.add(a);
        collection.add(d);
        collection.add(c);
        collection.add(b);
        collection.add(a);
        collection.add(d);
        collection.add(c);
        collection.add(b);
        collection.add(a);
        collection.add(d);
        collection.add(c);
        collection.add(b);
        collection.add(a);
        collection.add(d);
        collection.add(c);
        collection.add(b);
        collection.add(a);
        collection.add(d);

        studentList.insertAll(collection);


        assertEquals(20, studentList.size());
    }

    // Other method tests --------------------------------------------------
    @Test
    public void testFindMax(){
        assertEquals(a, simpleStudentList.findMax());
    }

    @Test
    public void testContains(){
        assertTrue(simpleStudentList.contains(c));
    }

    @Test
    public void testContainsForNonexistantItem(){
        assertFalse(simpleStudentList.contains(h));
    }

    @Test
    public void testDeleteMax(){
        simpleStudentList.deleteMax();
        assertEquals(b, simpleStudentList.getObjectAtPosition(5));
    }

    @Test
    public void testSize(){
        assertEquals(7,simpleStudentList.size());
    }

    @Test
    public void testIsEmpty(){
        assertTrue(studentList.isEmpty());
    }

    @Test
    public void testClear(){
        simpleStudentList.clear();
        assertTrue(studentList.isEmpty());
    }

    @Test
    public void testLargeDoubleQueue(){
        SimplePriorityQueue<Double> doubleQueue = new SimplePriorityQueue();
        for(int i = 0; i <1000; i++)
        {
            doubleQueue.insert(Math.random());
        }

        for(int i = 0; i < 999; i++){
            boolean test = doubleQueue.getObjectAtPosition(i) < doubleQueue.getObjectAtPosition(i+1);
            assertTrue(test);
        }
    }



}
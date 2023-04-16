package assign06;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BugReportTester {
    private SinglyLinkedList<Integer> testList;
    @BeforeEach
    void setUp(){
        testList = new SinglyLinkedList<>();
    }
    @Test
    void InsertTest(){
        testList.insert(0,5);
        testList.insert(0, 20);
        testList.insert(0,21);
        testList.insert(0,10);
        assertThrows(IndexOutOfBoundsException.class, () -> {testList.insert(4, 40);});
        testList.insert(3,30);


        testList.delete(0);
        assertEquals(4, testList.size());


    }
}

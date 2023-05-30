package assign09;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing for the HashTable class
 *
 * @author Bradley Bartelt, Isaac Hall
 * @Version 04/04/2023
 */
public class HashTester {
   private ArrayList<StudentGoodHash> students;
   private HashTable<StudentGoodHash,Integer> hash;

    @BeforeEach
    void setUp(){
        hash = new HashTable();
        students = new ArrayList<>();
        students.add(new StudentGoodHash(0000000, "first","first"));
        students.add(new StudentGoodHash(1111111, "AAAA","AAAA"));
        students.add(new StudentGoodHash(2222222, "BBBB","BBBB"));

        students.add(new StudentGoodHash(1, "a","a"));
        students.add(new StudentGoodHash(1, "b","b"));


    }

    @Test
    void simplePutTest(){
        hash.put(students.get(0), 90);
        assertEquals(90, hash.get(students.get(0)));

        hash.put(students.get(1), 88);
        assertEquals(88,hash.get(students.get(1)));

        assertEquals(88,hash.put(students.get(1),55));
        assertEquals(55,hash.get(students.get(1)));

        assertNull(hash.put(students.get(3),90));
    }
    @Test
    void hashToSameSpot(){
        hash.put(students.get(3),40);
        hash.put(students.get(4),50);

        assertEquals(50, hash.get(students.get(4)));
    }
    @Test
    void clearTest(){
        hash.put(students.get(3),40);
        hash.put(students.get(4),50);
        hash.put(students.get(0), 90);
        hash.put(students.get(1), 88);
        hash.put(students.get(2),70);

        hash.clear();

        assertEquals(0,hash.size());

        assertNull(hash.get(students.get(3)));

    }
    @Test
    void removeTest(){
        hash.put(students.get(3),40);
        hash.put(students.get(4),50);
        hash.put(students.get(0), 90);
        hash.put(students.get(1), 88);
        hash.put(students.get(2),70);

        hash.remove(students.get(0));
        hash.remove(students.get(4));
        hash.remove(students.get(3));
        assertNull(hash.get(students.get(0)));
        assertFalse(hash.containsKey(students.get(4)));
        assertFalse(hash.containsKey(students.get(3)));

        assertEquals(88, hash.remove(students.get(1)));
        hash.clear();

        assertNull(hash.remove(students.get(0)));
    }
    @Test
    void getTest(){
        hash.put(students.get(0), 90);
        assertEquals(90, hash.get(students.get(0)));
        assertNull(hash.get(students.get(1)));
    }
    @Test
    void sizeTest(){
        hash.put(students.get(3),40);
        hash.put(students.get(4),50);
        hash.put(students.get(0), 90);
        hash.put(students.get(1), 88);
        hash.put(students.get(2),70);

        assertEquals(5,hash.size());

        hash.remove(students.get(0));

        assertEquals(4, hash.size());
        hash.put(students.get(0), 90);
        assertEquals(5, hash.size());
    }

    @Test
    void containsTest(){
        assertFalse(hash.containsKey(students.get(2)));

        hash.put(students.get(3),40);
        hash.put(students.get(4),50);
        hash.put(students.get(0), 90);

        assertTrue(hash.containsKey(students.get(3)));
        assertTrue(hash.containsValue(90));
        assertFalse(hash.containsValue(100));
    }

    @Test
    void entriesTest(){
        hash.put(students.get(3),40);
        hash.put(students.get(4),50);
        hash.put(students.get(0), 90);
        hash.put(students.get(1), 88);
        hash.put(students.get(2),70);

        List<MapEntry<StudentGoodHash,Integer>> list = hash.entries();

        assertEquals(5, list.size());
        assertTrue(list.contains(new MapEntry(students.get(0),90)));
        assertTrue(list.contains(new MapEntry(students.get(1),88)));
        assertTrue(list.contains(new MapEntry(students.get(2),70)));
        assertTrue(list.contains(new MapEntry(students.get(3),40)));
        assertTrue(list.contains(new MapEntry(students.get(4),50)));

    }
    @Test
    void isEmptyTest(){
        assertTrue(hash.isEmpty());
        hash.put(students.get(0),90);
        assertFalse(hash.isEmpty());
        hash.remove(students.get(0));
        assertTrue(hash.isEmpty());

    }

    @Test
    void doubleArraySize(){
        for(int i = 0; i < 99; i ++)
            hash.put(new StudentGoodHash((int) (Math.random() * 1000000), "a", "b"), (int) Math.random() * 100);


        assertEquals(10,hash.backingArraySize());
        hash.clear();
        for(int i = 0; i < 120; i ++)
             hash.put(new StudentGoodHash((int) (Math.random() * 1000000), "a", "b"), (int) Math.random() * 100);

        assertEquals(20, hash.backingArraySize());

        Random r = new Random();
        String firstName;
        firstName = String.valueOf(((char)(r.nextInt(26) + 'a'))) + String.valueOf(((char)(r.nextInt(26) + 'a'))) +
                String.valueOf(((char)(r.nextInt(26) + 'a'))) + String.valueOf(((char)(r.nextInt(26) + 'a')));

        System.out.println(firstName);
    }
}

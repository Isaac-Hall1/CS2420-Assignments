package comprehensive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Testing class for DisjointSetList, our custom implementation
 *
 * @author Bradley Bartelt and Isaac Hall
 * @version 04/22/2023
 */
public class DisjointSetListTest {
    private DisjointSetList<Integer> set;
    @BeforeEach
    void setUp(){
        set = new DisjointSetList<>();
    }

    @Test
    void makeSetTest(){

        set.makeSet(1);
        set.makeSet(2);
        set.makeSet(3);
        set.makeSet(4);

        assertEquals(1, set.getRepresentative(1));
        assertEquals(2, set.getRepresentative(2));
        assertEquals(3, set.getRepresentative(3));
        assertEquals(4, set.getRepresentative(4));
    }
    @Test
    void unionTest(){
        set.makeSet(1);
        set.makeSet(2);
        set.makeSet(3);
        set.makeSet(4);


        set.makeSet(5);
        set.makeSet(6);

        set.makeSet(7);
        set.makeSet(8);

        set.union(1,2);
        set.union(2,3);

        set.union(4,5);
        set.union(4,6);
        set.union(1,4);

        set.union(7,8);
        set.union(4,7);

        assertEquals(1, set.getRepresentative(2));
        assertEquals(1, set.getRepresentative(3));
        assertEquals(1,set.getRepresentative(4));
        assertEquals(1,set.getRepresentative(6));

        assertEquals(1,set.getRepresentative(8));
        assertEquals(1,set.getRepresentative(7));
    }
    @Test
    void getRepresentativeTest(){
        set.makeSet(1);
        set.makeSet(2);
        set.makeSet(3);
        set.makeSet(4);

        set.makeSet(5);
        set.makeSet(6);

        set.makeSet(7);
        set.makeSet(8);

        set.union(1,2);
        set.union(2,3);

        set.union(4,5);
        set.union(4,6);
        set.union(1,4);

        set.union(7,8);
        set.union(4,7);

        assertEquals(1,set.getRepresentative(8));
        assertEquals(1,set.getRepresentative(7));


    }
}


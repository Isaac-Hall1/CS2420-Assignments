package comprehensive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Testing class for DisjointSetForest
 *
 * @author Bradley Bartelt and Isaac Hall
 * @version 04/22/2023
 */
public class DisjointSetForestTest {
    private DisjointSetForest<Integer> set;
    @BeforeEach
    void setUp(){
        set = new DisjointSetForest<>();
    }

    @Test
    void makeSetTest(){

        set.makeSet(1);
        set.makeSet(2);
        set.makeSet(3);
        set.makeSet(4);

        assertEquals(1, set.getNode(1).pointer.element);
        assertEquals(2, set.getNode(2).pointer.element);
        assertEquals(3, set.getNode(3).pointer.element);
        assertEquals(4, set.getNode(4).pointer.element);
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

        assertEquals(1, set.getNode(2).pointer.element);
        assertEquals(1, set.getNode(3).pointer.element);
        assertEquals(1,set.getNode(4).pointer.element);
        assertEquals(4,set.getNode(6).pointer.element);

        set.getRepresentative(8);
        assertEquals(1,set.getNode(8).pointer.element);
        assertEquals(1,set.getNode(7).pointer.element);
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

        set.getRepresentative(8);
        assertEquals(1,set.getNode(8).pointer.element);
        assertEquals(1,set.getNode(7).pointer.element);
    }
}

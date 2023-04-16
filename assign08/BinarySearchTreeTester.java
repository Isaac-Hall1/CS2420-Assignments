package assign08;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
public class BinarySearchTreeTester {
    private BinarySearchTree<Integer> tree;
    private Collection<Integer> col;
    @BeforeEach
    void setUp(){

        col = new ArrayList<>();
        col.add(9);
        col.add(5);
        col.add(12);
        col.add(3);
        col.add(7);
        col.add(10);
        col.add(15);
        col.add(2);
        col.add(4);
        col.add(6);
        col.add(8);
        col.add(11);

    }
    @Test
    void firstandLast(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        assertEquals(2,tree.first());
        assertEquals(15,tree.last());
    }
    @Test
    void containsTest(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        assertTrue(tree.contains(9));
    }
    @Test
    void containsTest1(){
        tree = new BinarySearchTree<Integer>();
        tree.add(9);

        assertFalse(tree.contains(8));
    }
    @Test
    void addTest(){
        tree = new BinarySearchTree<Integer>();
        tree.add(9);
        tree.add(5);
        tree.add(12);
        tree.add(3);
        tree.add(7);
        tree.add(10);
        tree.add(15);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(11);

        assertEquals(2,tree.head.getLeftmostNode().getData());
        assertEquals(3,tree.head.getLeftChild().getLeftChild().getData());
        assertEquals(4,tree.head.getLeftChild().getLeftChild().getRightChild().getData());

    }
    @Test
    void addCollectionTest(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        assertEquals(2,tree.head.getLeftmostNode().getData());
        assertEquals(3,tree.head.getLeftChild().getLeftChild().getData());
        assertEquals(4,tree.head.getLeftChild().getLeftChild().getRightChild().getData());

    }
    @Test
    void heightTest(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

       // System.out.println(tree.head.getLeftChild().height());
    }
    @Test
    void removeTest(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);
        
        tree.remove(10);
        assertEquals(11,tree.head.getRightChild().getLeftChild().getData());
        assertNull(tree.head.getRightChild().getLeftChild().getLeftChild());

        tree.clear();
        tree.remove(9);
    }
    @Test
    void toArrayListTest(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        for (Integer i: tree.toArrayList()) {
            System.out.print(i + " ");
        }
    }
    @Test
    void removeAdd(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        tree.remove(5);
        tree.remove(4);

        assertEquals(3,tree.head.getLeftChild().getData());
        assertEquals(2,tree.head.getLeftChild().getLeftChild().getData());
    }
    @Test
    void addExistingNumber(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        assertFalse(tree.add(9));
    }
    @Test
    void removeAllTest(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        assertTrue(tree.removeAll(col));
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);
        col.clear();
        col.add(80);
        assertFalse(tree.removeAll(col));


    }
    @Test
    void removeFew(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        tree.remove(9);
        tree.remove(8);
        tree.remove(11);
        assertNull(tree.head.getRightChild().getLeftChild().getRightChild());
        assertEquals(9,tree.size());

        tree.remove(12);
        assertEquals(8,tree.size());
        assertEquals(7, tree.head.getData());

        assertEquals(6,tree.head.getLeftChild().getRightChild().getData());
        assertEquals(10,tree.head.getRightChild().getData());
    }
    @Test
    void isClear() {
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        tree.clear();
        assertNull(tree.head);
        assertEquals(0,tree.size());
    }
    @Test
    void isEmptyTest(){
        tree = new BinarySearchTree<Integer>();
        assertTrue(tree.isEmpty());
        assertFalse(tree.contains(9));
        tree.addAll(col);
        assertFalse(tree.isEmpty());
    }
    @Test
    void size(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);

        assertEquals(12, tree.size());

        tree.remove(9);

        assertEquals(11, tree.size());

        tree.clear();

        assertEquals(0,tree.size());

    }
    @Test
    void containsAll(){
        tree = new BinarySearchTree<Integer>();
        tree.addAll(col);
        assertTrue(tree.containsAll(col));
        col.add(20);
        assertFalse(tree.containsAll(col));

    }
    @Test
    void LastTestEmpty(){
        tree = new BinarySearchTree<Integer>();
        assertThrows(NoSuchElementException.class, () -> {tree.last();});
    }

    @Test
    void firstTestEmpty(){
        tree = new BinarySearchTree<Integer>();
        assertThrows(NoSuchElementException.class, () -> {tree.first();});
    }

}

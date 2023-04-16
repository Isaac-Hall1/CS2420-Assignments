package assign10;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.List;

/**
 *
 * Methods to build a BinaryHeap that is backed by a basic array.
 *
 * @author Bradley Bartelt and Isaac Hall
 * @version 04/11/2023
 */

public class BinaryMaxHeap<E> implements PriorityQueue<E>{
    private E[] backingArray;
    private Comparator<E> cmp;
    private int size;

    /**
     * constructor for a MaxHeap of comparable type
     */
    public BinaryMaxHeap(){
        backingArray = (E[]) new Object[20];
        size = 0;
    }

    /**
     * constructor for a MaxHeap sorted using a comparator
     * @param cmp
     */
    public BinaryMaxHeap(Comparator<? super E> cmp){
        this.cmp = (Comparator<E>) cmp;
        backingArray = (E[]) new Object[20];
        size = 0;
    }

    /**
     * constructor for a MaxHeap of comparable type built from an input list
     * @param list
     */
    public BinaryMaxHeap(List<? extends E> list){
        backingArray = (E[]) new Object[list.size()+1];
        size = list.size();
        buildHeap((List<E>) list);
    }

    /**
     * constructor for a MaxHeap sorted using a comparator built from an input list
     * @param list
     * @param cmp
     */
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp){
        backingArray = (E[]) new Object[list.size()+1];
        size = list.size();
        this.cmp = (Comparator<E>) cmp;
        buildHeap((List<E>) list);
    }

    /**
     * The heapify method orders items in the binaryMaxHeap (buildHeap operation)
     */
    private void buildHeap(List<E> list){
        for(int i = 1; i < list.size() + 1; i++)
            backingArray[i] = list.get(i - 1);

        for(int i = size/2; i > 0; i--)
            percolateDown(i);
    }

    /**
     * looks upward in a BinaryMaxHeap, swaping the target value upward until the parent node is larger than the target
     * @param index
     */
    private void percolateUp(int index){

        int parent = index/2;
        // if we're at the root we return and are finished
        if(parent == 0){
            return;
        }
        E swap = backingArray[parent];
        // compares the child to the parent and if the child is greater we swap them
        if(compare(backingArray[index],backingArray[parent]) > 0){
            backingArray[parent] = backingArray[index];
            backingArray[index] = swap;
            percolateUp(parent);
        }
    }

    /**
     * looks downward in the BinaryMaxHeap, swaping the target value down until the child nodes are larger than the target.
     * @param index
     */
    private void percolateDown(int index) {

        int child;
        int lChild = index * 2;
        int rChild = index * 2 + 1;
        // if the leftChild is at an invalid index or is null we return
        if (lChild >= backingArray.length || backingArray[lChild] == null)
            return;
        // if the rightChild is at a valid index and isn't null
        if((rChild < backingArray.length) && backingArray[rChild] != null) {
            // we compare the leftChild to the rightChild
            if (compare(backingArray[lChild], backingArray[rChild]) > 0)
                child = lChild;
            else
                child = rChild;
        }
        else
            child = lChild;

        E swap = backingArray[child];
        // compares the found child to the given index and if the index is less than the child we move it down
        if (compare(backingArray[index], backingArray[child]) < 0) {
            backingArray[child] = backingArray[index];
            backingArray[index] = swap;
            percolateDown(child);
        }
    }

    /**
     * makes a comparison between two objects, deciding wheather or not to use a comparator, or if one isn't provided,
     * use the objects compareTo method.
     * @param o1
     * @param o2
     * @return
     */
    private int compare(E o1, E o2){
        if(cmp == null)
            return ((Comparable<? super E>)o1).compareTo(o2);
        else{
            return cmp.compare(o1,o2);
        }
    }

    /**
     * used to double the size of the backing array if it becomes filled up
     */
    private void doubleSize(){
        E[] newArr = (E[]) new Object[backingArray.length * 2];
        for(int i = 1; i < backingArray.length; i++){
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;
    }
    /**
     * Adds the given item to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param item
     */
    public void add(E item) {
        // if the array is full, we double the size
        if(size == backingArray.length - 1){
            doubleSize();
        }
        // place the item and then move it up the array
        backingArray[size + 1] = item;
        percolateUp(size + 1);
        size++;
    }

    /**
     * Returns, but does not remove, the maximum item this priority queue.
     * O(1)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    public E peek() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        return backingArray[1];
    }

    /**
     * Returns and removes the maximum item this priority queue.
     * O(log N)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    public E extractMax() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        E returnVal = backingArray[1];
        // replace the first value with the last value of the array and then move the value down the tree
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        percolateDown(1);
        size--;
        return returnVal;
    }

    /**
     * Returns the number of items in this priority queue.
     * O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this priority queue is empty, false otherwise.
     * O(1)
     */
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    /**
     * Empties this priority queue of items.
     * O(1)
     */
    public void clear() {
        backingArray = (E[]) new Object[backingArray.length];
        size = 0;
    }

    /**
     * Creates and returns an array of the items in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is needed for grading purposes. The root item
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    public Object[] toArray() {
        E[] newArr = (E[]) new Object[size];
        for(int i = 0; i < size; i++){
            newArr[i] = (E) backingArray[i + 1];
        }
        return newArr;
    }
}

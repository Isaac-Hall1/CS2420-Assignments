/**
 * @Author Bradley Bartelt and Isaac Hall
 */
package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>{

    private E[] array;
    Comparator<? super E> cmp;
    private int lastElementPos = -1;
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(){
        array = (E[]) new Object[16];
    }
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp){
        array = (E[]) new Object[16];
        this.cmp = cmp;
    }

    /**
     * returns the object at the specified position in priority que.
     * @return E
     */
    public E getObjectAtPosition(int i) {
        return array[i];
    }
    /**
     * Retrieves, but does not remove, the maximum element in this priority
     * queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E findMax() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("Array is empty");
        }
        return array[lastElementPos];
    }
    /**
     * Retrieves and removes the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E deleteMax() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("Array is empty");
        }
        int lastElementDelete = lastElementPos;
        lastElementPos -= 1;
        return array[lastElementDelete] = null;

    }
    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item - the element to insert
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insert(E item) {
        //if the array is full, length is doubled
        if (size() == array.length) {
            E[] newArray = (E[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }

        //runs binary search and saves the return value
        int binarySearch = binarySearch(item);

        //moves elements over to the right to make room at the index where item needs to be inserted
        for(int i = lastElementPos; i >= binarySearch; i--) {
            array[i + 1] = array[i];

        }
        //adds the item at the correct index
        array[binarySearch] = item;
        lastElementPos++;
    }
    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll - the collection of elements to insert
     */
    @Override
    public void insertAll(Collection coll) {
        Iterator iterator = coll.iterator();
        // utilizes an iterator to read a collection similar to how you can scan a file
        while (iterator.hasNext()){
            insert((E) iterator.next());
        }
    }
    /**
     * Indicates whether this priority queue contains the specified element.
     *
     * @param item - the element to be checked for containment in this priority
    queue
     */
    @Override
    public boolean contains(E item) {
        int binarySearch = binarySearch(item);
        int compareValue = 0;
        //if there are no values, item can't be in array
        if(array[0] == null){
            return false;
        }
        //checks type to call correct comparison method
        if (cmp != null)
            compareValue = cmp.compare(array[binarySearch], item);
        else
            compareValue = ((Comparable<? super E>) item).compareTo(array[binarySearch]);

        if(compareValue == 0){
            return true;
        }
        else
            return false;
    }
    /**
     * @return the number of elements in this priority queue
     */
    @Override
    public int size() {
        return lastElementPos + 1;
    }
    /**
     * @return true if this priority queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if(array[0] == null){
            return true;
        }
        return false;
    }
    /**
     * Removes all of the elements from this priority queue. The queue will be
     * empty when this call returns.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (E[]) new Object[array.length];
        lastElementPos = -1;
    }

    /**
     *
     * Utilizes a Binary Search to look through the array with a specific object(key)
     *
     * @param key
     * @return The index of where an item fits in the list or a 0 if the list contains an item
     */
    @SuppressWarnings("unchecked")
    private int binarySearch(E key) {
        if (array[0] == null)
            return 0;

        int first = 0;
        int last = array.length - 1;

        last = lastElementPos;

        int mid = last/2;
        while (first <= last) {
            int compareValue = 0;

            //checks type to call correct comparison method
            if (cmp != null)
                compareValue = cmp.compare(array[mid], key);
            else
                compareValue = ((Comparable<? super E>) key).compareTo(array[mid]);

            // if key > array[mid] then we look to the right
            if (compareValue > 0) {
                first = mid + 1;
            }
            // if key equals array[mid] the array contains the value
            else if (compareValue == 0) {
                return mid;
            }
            // if key < array[mid] the then we look to the left
            else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        
        // returns the index of where to place the
        if (first > last) {
            return first;
        }
        return -1;
    }
}

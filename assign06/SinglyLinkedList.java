/**
 * Creates an instance of a linked list
 *
 * @Authors Bradley B and Isaac Hall
 * @Version 3/2/23
 */
package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>{
    private Node nodeHead;
    private Node nodeTail;
    private int size;
    public SinglyLinkedList(){
        nodeHead = null;
        nodeTail = null;
        size = 0;
    }
    class Node{
        E data;
        Node next;
        public Node(E data){
            this.data = data;
            this.next = null;
        }
    }
    /**
     * Inserts an element at the beginning of the list.
     * O(1) for a singly-linked list.
     *
     * @param element - the element to add
     */
    public void insertFirst(E element){
        //creates a new node containing the correct information
        Node newNode = new Node(element);
        //if it's the first node being added to the linked list
        if(nodeHead == null){
            nodeHead = newNode;
            nodeTail = newNode;
        }
        //if nodes have been added previously
        else{
            newNode.next = nodeHead;
            nodeHead = newNode;
        }
        size++;
    }

    /**
     * Inserts an element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    public void insert(int index, E element) throws IndexOutOfBoundsException{


        if((index >= size || index < 0) && index != 0){
            throw new IndexOutOfBoundsException("Provided index out of bounds.");
        }

        //if inserting at position 0 just use insertFirst method
        if(index == 0) {
            insertFirst(element);
            return;
        }


        Node currentNode = nodeHead;

        //loops through the list and stops once the provided index has been reached
        //at that moment inserts the new node by changing the effected pointers of nodes around it
        for(int i = 0; i < size; i++){
            if(i == index-1){
                Node nextNode = currentNode.next;
                Node newNode = new Node(element);
                currentNode.next = newNode;
                newNode.next = nextNode;
            }
            else
                currentNode = currentNode.next;
        }
        size++;

    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() throws NoSuchElementException{
        if(size <= 0){
            throw new NoSuchElementException("list is empty");
        }
        return nodeHead.data;
    }

    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    public E get(int index) throws IndexOutOfBoundsException{
        if(index > size - 1 || index < 0){
            throw new IndexOutOfBoundsException("Provided index out of bounds.");
        }
        Node currentNode = nodeHead;
        //moves through the list and returns the element at the specified index
        for(int i = 0; i <= index; i++){
            if(i == index){
                return currentNode.data;
            }
            else
                currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public E deleteFirst() throws NoSuchElementException{
        if(size <= 0){
            throw new NoSuchElementException("list is empty");
        }
        E temp = nodeHead.data;
        nodeHead = nodeHead.next;
        size--;
        return temp;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    public E delete(int index) throws IndexOutOfBoundsException{
        if(index > size - 1 || index < 0){
            throw new IndexOutOfBoundsException("Provided index out of bounds.");
        }
        Node currentNode = nodeHead;
        //loops through the list until the specified index is reached
        //pointer at previous value in then pointed to the next value, essentially deleting the selected value
        for(int i = 0; i < index; i++){
            if(i == index - 1){
                Node nextNode = currentNode.next.next;
                Node returnNode = currentNode.next;
                currentNode.next = nextNode;
                size--;
                return returnNode.data;
            }
            else
                currentNode = currentNode.next;
        }

        nodeHead = nodeHead.next;
        size--;
        return currentNode.data;
    }


    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    public int indexOf(E element){
        Node currentNode = nodeHead;
        //loops through the list comparing each element to the target until a match is found
        for(int i = 0; i < size; i++){
            if(currentNode.data.equals(element)){
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    public int size(){
        return size;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }

    /**
     * Removes all of the elements from this list.
     * O(1) for a singly-linked list.
     */
    public void clear(){
        nodeHead = null;
        nodeTail = null;
        size = 0;
    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
    public Object[] toArray(){
        Object[] returnArray = new Object[size];
        Node currentNode = nodeHead;
        //loops through the list and assigns each value to the same index in our coresponding return array.
        for(int i = 0; i < size;i++){
            returnArray[i] = currentNode.data;
            if(i == size - 1){
                break;
            }
            currentNode = currentNode.next;
        }
        return returnArray;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    public Iterator<E> iterator(){
        return new stackIterator();
    }
    public class stackIterator implements Iterator{
        int pos;
        boolean nextCalled;

        public stackIterator(){
            pos = 0;
            nextCalled = false;
        }
        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public Object next() {
            //end of list is reached
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            E next = get(pos);
            pos++;
            nextCalled = true;
            return next;
        }

        @Override
        public void remove() {
            //doesn't allow user to delete twice in a row
            if(!nextCalled){
                throw new IllegalStateException();
            }
            nextCalled = false;
            delete(pos - 1);
            pos--;
        }
    }
}

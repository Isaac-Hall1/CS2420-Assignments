/**
 *
 * This is a stack that is backed by a linkedList
 *
 * @Author Bradley Bartelt and Isaac Hall
 * @Version 3/2/2023
 *
 */
package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {
    private SinglyLinkedList stackList;

    public LinkedListStack() {
        stackList = new SinglyLinkedList();
    }

    /**
     * Removes all of the elements from the stack.
     */
    public void clear(){
        // clears entire stack using a singlyLinkedList function
        stackList.clear();
    }

    /**
     * @return true if the stack contains no elements; false, otherwise.
     */
    public boolean isEmpty(){
        //Checks if the list is empty using a singlyLinkedList method
        return stackList.isEmpty();
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public E peek() throws NoSuchElementException{
        // gets the first element from our singlyLinkedList which returns the top element of a stack
        return (E) stackList.getFirst();
    }

    /**
     * Returns and removes the item at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public E pop() throws NoSuchElementException{
        // deletes the first element form our singlyLinkedList as well as returning it
        return (E) stackList.deleteFirst();
    }

    /**
     * Adds a given element to the stack, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    public void push(E element){
        // adds a new element to the top of the stack using the signlyLinkedList's insertFirst method
        stackList.insertFirst(element);
    }

    /**
     * @return the number of elements in the stack
     */
    public int size(){
        //returns the size of that using the singlyLinkedList.size() method.
        return stackList.size();
    }

}

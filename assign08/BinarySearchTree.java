/**
 *
 * Represents a Binary Search Tree, using Binary Nodes.
 *
 * @Version 03/23/2023
 * @Author Isaac Hall and Bradley Bartelt
 *
 */
package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>{

    public BinaryNode head;

    private int size;

    public BinarySearchTree(){
        this.head = null;
        this.size = 0;
    }
    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     */
    public boolean add(Type item){
        // if the tree is empty we make the item the head.
        if(head == null){
            head = new BinaryNode(item);
            size++;
            return true;
        }

        BinaryNode currentNode = head;

        while(true) {
            int compareVal = item.compareTo((Type)currentNode.getData());
            // if the item exists we return false and do not add it to the tree.
            if(compareVal == 0){
                return false;
            }
            // if item is less than the value of the current node and the current node's left child doesn't exist
            // we make the left child node and append the value of item to it
            if(compareVal < 0){
                if(currentNode.getLeftChild() == null) {
                    currentNode.setLeftChild(new BinaryNode(item));
                    size++;
                    return true;
                }
                else {
                    // else we move further along the tree
                    currentNode = currentNode.getLeftChild();
                }
            }
            // if the item is greater than the current item
            if(compareVal > 0){
                // and the right child node doesn't exist
                if(currentNode.getRightChild() == null) {
                    // creates a new node in the position of the right child of current node and appends the value
                    // of item to it.
                    currentNode.setRightChild(new BinaryNode(item));
                    size++;
                    return true;
                }
                else {
                    // else we increment further to the right
                    currentNode = currentNode.getRightChild();
                }
            }
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     */
    public boolean addAll(Collection<? extends Type> items){
        boolean returnVal = false;
        for (Type i: items) {
            // if an item is added we set return val to true and continue adding items after the loop we return returnVal
             if(add(i) == true){
                 returnVal = true;
             }
        }
        return returnVal;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    public void clear(){
        // if head is null it empties the entire tree
        head = null;
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     */
    public boolean contains(Type item){
        BinaryNode currentNode = head;
        if(size == 0){
            return false;
        }
        while(true) {
            int compareVal = item.compareTo((Type)currentNode.getData());
            // if the item exists we return true
            if(compareVal == 0){
                return true;
            }
            if(compareVal < 0){
                // if the leftChild doesn't exist than neither does the value so we return false
                if(currentNode.getLeftChild() == null) {
                    return false;
                }
                // if it does exist we increment further
                else {
                    currentNode = currentNode.getLeftChild();
                }
            }
            if(compareVal > 0){
                // if the rightChild doesn't exist than neither does the value so we return false
                if(currentNode.getRightChild() == null) {
                    return false;
                }
                // if it does exist we increment further
                else {
                    currentNode = currentNode.getRightChild();
                }
            }
        }
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     */
    public boolean containsAll(Collection<? extends Type> items){
        boolean returnVal = true;
        for (Type i: items) {
            // if one item in the list isnt in the tree we return false
            if(contains(i) == false){
                return false;
            }
        }
        return returnVal;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    public Type first() throws NoSuchElementException{
        if(head == null){
            throw new NoSuchElementException();
        }

        return (Type) head.getLeftmostNode().getData();
    }

    /**
     * Returns true if this set contains no items.
     */
    public boolean isEmpty(){
        //if the head is null the list is empty
        if(head == null){
            return true;
        }
        return false;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    public Type last() throws NoSuchElementException{
        if(head == null){
            throw new NoSuchElementException("Tree is empty");
        }
        return (Type) head.getRightmostNode().getData();
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */
    public boolean remove(Type item){
        BinaryNode currentNode = head;
        BinaryNode previous = null;
        // checks to make sure the set isn't empty
        if(size ==0){
            return false;
        }
        // removes the head when the set is only comprised of the head
        if(size == 1 && item.equals(head.getData())){
            head = null;
            return true;
        }

        while(true) {
            int compareVal = item.compareTo((Type)currentNode.getData());
            if(compareVal == 0){

                //both children null (leaf)
                if(currentNode.getRightChild() == null && currentNode.getLeftChild() == null){
                    // if the leaf is a right child we remove the leaf
                    if(previous.getRightChild() != null && previous.getRightChild().getData().equals(item)){
                        previous.setRightChild(null);
                    }
                    // if its a left child we remove it as well
                    else{
                        previous.setLeftChild(null);

                    }
                    size--;
                    return true;
                }

                if(currentNode.getRightChild() == null){
                    // we get the leftChilds Right most data and save its value
                    Type data = (Type) currentNode.getLeftChild().getRightmostNode().getData();
                    // we remove that leaf
                    remove(data);
                    // we then replace the data of the node we want to remove with the data of data
                    currentNode.setData(data);
                    return true;
                }
                // same as above just with the right childs left most node's data
                if(currentNode.getLeftChild() == null){
                    Type data = (Type) currentNode.getRightChild().getLeftmostNode().getData();
                    remove(data);
                    currentNode.setData(data);
                    return true;
                }


                //if the node to remove has two children we check which one has a greater height
                // so that the set will be slightly more balanced
                if(currentNode.getLeftChild().height() >= currentNode.getRightChild().height()){
                    // same technique as above
                    Type data = (Type) currentNode.getLeftChild().getRightmostNode().getData();
                    remove(data);
                    currentNode.setData(data);
                    return true;
                }
                else{
                    // same technique as above
                    Type data = (Type) currentNode.getRightChild().getLeftmostNode().getData();
                    remove(data);
                    currentNode.setData(data);
                    return true;
                }
            }

            // searches set to find node to remove
            if(compareVal < 0){
                if(currentNode.getLeftChild() == null) {
                    return false;
                }
                else {
                    // increments current node and keeps track of previous node
                    previous = currentNode;
                    currentNode = currentNode.getLeftChild();
                }
            }

            // searches set to find node to remove
            if(compareVal > 0){
                if(currentNode.getRightChild() == null) {
                    return false;
                }
                else {
                    // increments current node and keeps track of previous node
                    previous = currentNode;
                    currentNode = currentNode.getRightChild();
                }
            }
        }
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     */
    public boolean removeAll(Collection<? extends Type> items){
        boolean returnVal = false;
        for(Type i: items){
            // if a single item is removed we return true once the for each loop ends
            if(remove(i) == true)
                returnVal = true;
        }
        return returnVal;
    }

    /**
     * Returns the number of items in this set.
     */
    public int size(){
        return size;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    public ArrayList<Type> toArrayList(){

        ArrayList<Type> output = new ArrayList<Type>();
        if (head == null){
            return output;
        }

        Stack<BinaryNode<Type>> order = new Stack<>();
        BinaryNode<Type> currentNode = head;

        while(!order.isEmpty() || currentNode != null){
            // if the node exists
            if(currentNode != null) {
                // add it to the queue and increment currentNode to the left
                order.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            else{
                // remove the first item of the queue and add it to the output list
                BinaryNode<Type> n = order.pop();
                output.add(n.getData());
                // sets current node to the right child of the node that was removed from the queue
                currentNode= n.getRightChild();
            }
        }

        return output;
    }

}

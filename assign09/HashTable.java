package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements a HashTable and all of its associated methods
 *
 * @Author Isaac Hall and Bradley Bartelt
 * @Version 04/04/2023
 */
public class HashTable<K,V> implements Map<K,V>{
    private ArrayList<LinkedList<MapEntry<K,V>>> backingArray;
    private int size;
    public boolean collisions;

    /**
     * constructor
     */
    public HashTable(){
        backingArray = new ArrayList<>(10);

        for(int i = 0; i < 10; i++){
            // creates a new HashTable full of empty linkedLists
            backingArray.add(new LinkedList<MapEntry<K,V>>());
        }

        size = 0;

        collisions = false;
    }

    /**
     * Removes all mappings from this map.
     *
     * O(table length) for quadratic probing or separate chaining
     */
    public void clear(){
        for(int i = 0; i < backingArray.size(); i++){
            // clears each linked list removing all the MapEntries
            backingArray.get(i).clear();
        }
        size = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     *
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return true if this map contains the key, false otherwise
     */
    public boolean containsKey(K key){
        // gives the index to place the item
        int index = Math.abs(key.hashCode() % backingArray.size());
        LinkedList<MapEntry<K,V>> list = backingArray.get(index);
        Iterator it = list.iterator();
        while(it.hasNext()){
            // iterates over linked list at given index if it exists returns true
            MapEntry<K,V> keyValue = (MapEntry<K, V>) it.next();
            if(key.equals(keyValue.getKey())){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether this map contains the specified value.
     *
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @param value
     * @return true if this map contains one or more keys to the specified value,
     *         false otherwise
     */
    public boolean containsValue(V value){
        //looks at every linkedlist in the array
        for(int i = 0; i < backingArray.size(); i++) {
            LinkedList<MapEntry<K,V>> list = backingArray.get(i);
            Iterator it = list.iterator();
            // iterates over linked list, returns true if value is found
            while (it.hasNext()) {
                MapEntry<K, V> mapValue = (MapEntry<K, V>) it.next();
                if (value.equals(mapValue.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a List view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     *
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    public List<MapEntry<K, V>> entries(){
        List<MapEntry<K,V>> output = new ArrayList<>();
        //iterates over every index in the array
        for(int i = 0; i < backingArray.size(); i++) {
            LinkedList<MapEntry<K,V>> list = backingArray.get(i);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                // iterates over linked list and adds the value to the output list
                output.add((MapEntry<K, V>) it.next());

            }
        }
        return output;
    }

    /**
     * Gets the value to which the specified key is mapped.
     *
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return the value to which the specified key is mapped, or null if this map
     *         contains no mapping for the key
     */
    public V get(K key){
        //calls hash function to find what position to look at in the array
        int index = Math.abs(key.hashCode() % backingArray.size());

        LinkedList<MapEntry<K,V>> list = backingArray.get(index);
        Iterator it = list.iterator();
        // iterates over linked list,
        while(it.hasNext()){
            MapEntry<K,V> keyValue = (MapEntry<K, V>) it.next();
            if(key.equals(keyValue.getKey())){
                //if keys match, return the value
                return keyValue.getValue();
            }
        }
        //if key isn't found, return null
        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     *
     * O(1) for quadratic probing or separate chaining
     *
     * @return true if this map contains no mappings, false otherwise
     */
    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     *
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @param value
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     *

     */
    public V put(K key, V value){
        // doubles size of backing array if the load factor exceeds 10.0
        if(size / backingArray.size() >= 10){
            doubleArraySize();
        }
        int index = Math.abs(key.hashCode() % backingArray.size());
        LinkedList<MapEntry<K,V>> list = backingArray.get(index);
        Iterator it = list.iterator();
        if(!backingArray.get(index).isEmpty()){
            collisions = true;
        }
        // iterates over a linked list at the given index to check if the key already exists
        while(it.hasNext()){
            MapEntry<K,V> keyValue = (MapEntry<K, V>) it.next();
            if(key.equals(keyValue.getKey())){
                // if the key exists, saves the keys value then sets the key pair to the new value
                var output = keyValue.getValue();
                keyValue.setValue(value);
                // returns saved value
                return output;
            }
        }

        // if the key didn't exist in the linked list at the given index, adds it to the bucket and returns null
        backingArray.get(index).addLast(new MapEntry(key,value));
        size++;
        return null;

    }

    /**
     * helper method used for doubling the size of the backing array once the load factor is exceeded. Mappings are rehashed
     * when the array size is doubled.
     */
    private void doubleArraySize(){
        //makes new array double the size of the previous
        int newSize = backingArray.size() * 2;
        ArrayList<LinkedList<MapEntry<K,V>>> newArray = new ArrayList<>(newSize);
        //fills it with map entries
        for(int i = 0; i < newSize; i++){
            newArray.add(new LinkedList<MapEntry<K,V>>());
        }

        size = 0;

        //iterates over the original backing array
        for(int i = 0; i < backingArray.size(); i++) {
            LinkedList<MapEntry<K,V>> list = backingArray.get(i);
            Iterator it = list.iterator();


            //iterates over the linked list in every position in the array
            while (it.hasNext()) {
                var mapVal = (MapEntry<K,V>) it.next();
                //rehash
                int index = Math.abs(mapVal.getKey().hashCode() % newSize);
                if(!newArray.get(index).isEmpty()){
                    collisions = true;
                }
                //add to the new backing array
                newArray.get(index).addLast(new MapEntry(mapVal.getKey(),mapVal.getValue()));
                size++;
            }

        }
        //point backing array twards the new doubled in size array
        backingArray = newArray;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V remove(K key){
        //hashes the key to find the array position
        int index = Math.abs(key.hashCode() % backingArray.size());
        LinkedList<MapEntry<K,V>> list = backingArray.get(index);
        Iterator it = list.iterator();
        while(it.hasNext()) {
            // iterates over linked list
            MapEntry<K, V> keyValue = (MapEntry<K, V>) it.next();
            //if key is found, the mapping is removed
            if (key.equals(keyValue.getKey())) {
                var output = keyValue.getValue();
                list.remove(keyValue);
                size--;
                return output;
            }
        }
        return null;
    }

    /**
     * Determines the number of mappings in this map.
     *
     * O(1) for quadratic probing or separate chaining
     *
     * @return the number of mappings in this map
     */
    public int size(){
        return size;
    }

    /**
     * returns the size of the array backing the HashTable, useful for testing
     * @return int
     */
    public int backingArraySize(){
        return backingArray.size();
    }
}

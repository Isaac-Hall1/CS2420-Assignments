package comprehensive;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Our custom implimentatoin of a DisjoinSet
 * @param <E>
 */
public class DisjointSetList<E> implements DisjointSet<E>{

    private LinkedList<HashHead<E>> list;

    /**
     * a class that stores a hashset, representing a one particular disjoint set in our big disjoint set, it also
     * contains a variable denoting the representative for easy access.
     * @param <E>
     */
    public class HashHead<E>{
        E rep;
        HashSet<E> set;

        /**
         * constructor for HashHead
         * @param element
         */
        public HashHead(E element){
           rep = element;
           set = new HashSet<>();
           set.add(element);
        }

        /**
         * calls the hashset's contains method
         * @param element
         * @return
         */
        public boolean contains(E element){
            return set.contains(element);
        }

        /**
         * calls the hashset's add method
         * @param element
         */
        public void add(E element){
            set.add(element);
        }

        /**
         * calls the hashset's remove method
         * @param element
         */
        public void remove(E element){
            set.remove(element);
        }

        /**
         * calls the hashset's size method
         * @return
         */
        public int size(){
            return set.size();
        }
    }

    /**
     * constructor for a DisjointSetList
     */
    public DisjointSetList(){
        list = new LinkedList<>();
    }

    /**
     * Creates a new set containing a single element (data). This element must not be present in any other existing
     * sets
     *
     * @param element
     */
    public void makeSet(E element) {
        list.add(new HashHead(element));
    }

    /**
     * Returns the representative of a disjoint given an existing element in the set
     * returns null if the element doesnt exist
     *
     * @param element
     * @return Element
     */
    public E getRepresentative(E element) {
        Iterator<HashHead<E>> it = list.iterator();
        // goes over every HashHead looking for element
        while(it.hasNext()){
            HashHead<E> temp = it.next();
            if(temp.contains(element)){
                // once the element is found, returns the current HashHead's rep
                return temp.rep;
            }
        }
        return null;
    }

    /**
     * joins two disjointed sets together
     * the larger disjointed set's representative becomes the overall rep
     *
     * @param e1
     * @param e2
     */
    public void union(E e1, E e2) {
        HashHead<E> head1 = null;
        HashHead<E> head2 = null;

        int stop = 0;

        Iterator<HashHead<E>> it = list.iterator();
        // goes over every HashHead Looking for the HashHead that each given element exists in
        while(it.hasNext()){
            HashHead<E> temp = it.next();
            if(temp.contains(e1 )&& temp.contains(e2))
                return;
            if(temp.contains(e1)){
                head1 = temp;
                stop++;
                it.remove();
            }
            if(temp.contains(e2)){
                head2 = temp;
                stop++;
                it.remove();
            }
            if(stop >=2){
                break;
            }
        }
        // checks if each set has the same rep
        if(head1.rep == head2.rep){
            list.add(head1);
            list.add(head2);
            return;
        }
        Iterator<E> iter;
        // picks the overall set after unioning by deciding which set is bigger.
        if(head1.size() > head2.size()){
           iter = head2.set.iterator();
           // adds all values of the head's hashset to the bigger set
           while(iter.hasNext()){
               head1.add(iter.next());
           }
            list.add(head1);
        }

        else if(head1.size() < head2.size()){
            iter = head1.set.iterator();
            while(iter.hasNext()){
                head2.add(iter.next());
            }
            list.add(head2);
        }
        // if equal it picks the first HashHead
        else{
            iter = head2.set.iterator();
            while(iter.hasNext()){
                head1.add(iter.next());
            }
            list.add(head1);
        }

    }
}

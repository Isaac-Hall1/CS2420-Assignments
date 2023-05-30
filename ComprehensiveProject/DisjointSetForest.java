package comprehensive;

import java.util.HashMap;

/**
 * Implementation of DisjointSetForest
 *
 * @author Bradley Bartelt and Isaac Hall
 * @version 04/22/2023
 */
public class DisjointSetForest<E> implements DisjointSet<E> {
    private HashMap<E,Node> map;

    /**
     * Node class that represents the different nodes in our disjointSetForest.
     * Each node has a pointer pointing to another node, and a rank.
     */
    public class Node{
        Node pointer;
        E element;
        int rank;
        public Node(E element){
            this.element = element;
            pointer = this;
            rank = 1;
        }
    }

    /**
     * constructor for the disjointSetForest, creates a new hashmap
     */
    public DisjointSetForest(){
        map = new HashMap<>();
    }


    /**
     * Creates a new set containing a single element (data). This element must not be present in any other existing
     * sets
     *
     * @param element
     */
    public void makeSet(E element) {
        Node rep = new Node(element);
        map.put(element, rep);
    }

    /**
     * Returns the representative of a disjoint given an existing element in the set
     *
     * @param element
     * @return Element
     */
    public E getRepresentative(E element) {
        return (find(map.get(element))).element;
    }

    /**
     *
     * recursive method behind getRepresentative
     * changes the nodes pointer to the root and returns the root node
     *
     * @param node
     * @return Node
     */
    private Node find(Node node){
        // stops once the rep node is found and recursively makes everything point to it
        if(node.pointer != node)
            node.pointer = find(node.pointer);
        return node.pointer;
    }


    /**
     * joins two disjointed sets together
     * the larger disjointed set's representative becomes the overall rep
     *
     * @param e1
     * @param e2
     */
    public void union(E e1, E e2) {
        // check the rep's of the two nodes, if equal just return
        Node rep1 = (Node) map.get(getRepresentative(e1));
        Node rep2 = (Node) map.get(getRepresentative(e2));
        if(rep1 == rep2){
            return;
        }
        // assigns the lead rep dependant on rank
        if(rep1.rank > rep2.rank){
            rep2.pointer = rep1;
        }
        else if(rep1.rank < rep2.rank){
            rep1.pointer = rep2;
        }
        else {
            rep2.pointer = rep1;
            rep1.rank++;
        }

    }

    /**
     * Method used for testing, allows us to access the actual nodes outside this file and check their pointers
     * @param element
     * @return node
     */
    public Node getNode(E element){
        // accessor for map
        return map.get(element);
    }
}

package comprehensive;

/**
 * Interface to represent a disjointed set
 *
 * @author Isaac Hall and Bradley Bartelt
 * @version 04/18/23
 *
 */
public interface DisjointSet<E> {

    /**
     * Creates a new set containing a single element (data). This element must not be present in any other existing
     * sets
     *
     * @param element
     */
    public void makeSet(E element);

    /**
     *
     * Returns the representative of a disjoint given an existing element in the set
     *
     * @param element
     * @return Element
     */
    public E getRepresentative(E element);

    /**
     * joins two disjointed sets together
     * the larger disjointed set's representative becomes the overall rep
     *
     * @param element1
     * @param element2
     */
    public void union(E e1, E e2);
}

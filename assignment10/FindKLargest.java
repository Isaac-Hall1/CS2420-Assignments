package assign10;

import java.util.*;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Erin Parker, Isaac Hall, Bradley Bartelt
 * @version 04/11/2023
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {

		BinaryMaxHeap<E> heap = new BinaryMaxHeap(items);
		List<E> returnList = new ArrayList();
		if(k < 0 || k > heap.size())
			throw new IllegalArgumentException();
		// adds the maximum element to the return list k times
		for(int i = 0; i < k; i++){
			returnList.add(heap.extractMax());
		}
		return returnList;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		BinaryMaxHeap<E> heap = new BinaryMaxHeap(items,cmp);
		List<E> returnList = new ArrayList();
		if(k < 0 || k > heap.size())
			throw new IllegalArgumentException();
		// adds the maximum element to the return list k times
		for(int i = 0; i < k; i++){
			returnList.add(heap.extractMax());
		}
		return returnList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		if(k < 0 || k > items.size())
			throw new IllegalArgumentException();
		int count = 0;
		List<E> returnList = new ArrayList();
		Collections.sort(items);
		Collections.reverse(items);
		Iterator it = items.iterator();
		// adds the maximum element to the return list k times
		while(it.hasNext() && count < k){
			returnList.add((E) it.next());
			count++;
		}
		return returnList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if(k < 0 || k > items.size())
			throw new IllegalArgumentException();
		int count = 0;
		List<E> returnList = new ArrayList();
		Collections.sort(items,cmp);
		Collections.reverse(items);
		Iterator it = items.iterator();
		// adds the maximum element to the return list k times
		while(it.hasNext() && count < k){
			returnList.add((E) it.next());
		}
		return returnList;
	}
}
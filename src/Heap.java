import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * TODO: Complete the implementation of this class.
 *
 * The keys in the heap must be stored in an array.
 *
 * There may be duplicate keys in the heap.
 *
 * The constructor takes an argument that specifies how objects in the
 * heap are to be compared. This argument is a java.util.Comparator,
 * which has a compare() method that has the same signature and behavior
 * as the compareTo() method found in the Comparable interface.
 *
 * Here are some examples of a Comparator<String>:
 *    (s, t) -> s.compareTo(t);
 *    (s, t) -> t.length() - s.length();
 *    (s, t) -> t.toLowerCase().compareTo(s.toLowerCase());
 *    (s, t) -> s.length() <= 3 ? -1 : 1;
 */

public class Heap<E> implements PriorityQueue<E> {
	protected List<E> keys;
	private Comparator<E> comparator;

	/**
	 * TODO
	 *
	 * Creates a heap whose elements are prioritized by the comparator.
	 */
	public Heap(Comparator<E> comparator)
	{
		keys = new ArrayList<E>();
		this.comparator = comparator;

	}

	/**
	 * Returns the comparator on which the keys in this heap are prioritized.
	 */
	public Comparator<E> comparator() {
		return comparator;
	}

	/**
	 * TODO
	 *
	 * Returns the top of this heap. This will be the highest priority key.
	 * @throws NoSuchElementException if the heap is empty.
	 */
	public E peek()
	{
		if(keys.isEmpty())
		{
			throw new NoSuchElementException();
		}
		return keys.get(0);
	}

	/**
	 * TODO
	 *
	 * Inserts the given key into this heap. Uses siftUp().
	 */
	public void insert(E key) {

	}

	/**
	 * TODO
	 *
	 * Removes and returns the highest priority key in this heap.
	 * @throws NoSuchElementException if the heap is empty.
	 */
	public E delete() {
		return null;
	}

	/**
	 * TODO
	 *
	 * Restores the heap property by sifting the key at position p down
	 * into the heap.
	 */
	public void siftDown(int p)
	{

	}

	/**
	 * TODO
	 *
	 * Restores the heap property by sifting the key at position q up
	 * into the heap. (Used by insert()).
	 */
	public void siftUp(int q)
	{

	}

	/**
	 * TODO
	 *
	 * Exchanges the elements in the heap at the given indices in keys.
	 */
	public void swap(int i, int j)
	{
		E tmp = keys.get(i);
		keys.add(i, keys.get(j));
		keys.add(j, tmp);
	}

	/**
	 * Returns the number of keys in this heap.
	 */
	public int size() {
		return keys.size();
	}

	/**
	 * Returns a textual representation of this heap.
	 */
	public String toString() {
		return keys.toString();
	}

	/**
	 *
	 *
	 * Returns the index of the left child of p.
	 */
	public static int getLeft(int p)
	{
		return (p * 2) + 1;
	}

	/**
	 *
	 *
	 * Returns the index of the right child of p.
	 */
	public static int getRight(int p)
	{
		return (p + 1) * 2;
	}

	/**
	 *
	 *
	 * Returns the index of the parent of p.
	 */
	public static int getParent(int p)
	{
		return (p - 1) / 2;
	}
}

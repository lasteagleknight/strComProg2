/*
 * Modified from book to implement PQ interface.
 * 
 * *** in comments marks change points
 * 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/*************************************************************************
 * Compilation: javac OrderedArrayMaxPQ.java Execution: java OrderedArrayMaxPQ
 * 
 * Priority queue implementation with an ordered array.
 * 
 * Limitations ----------- - no array resizing - does not check for overflow or
 * underflow.
 * 
 * 
 *************************************************************************/

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> implements PQ<Key> // ***
{
	private Key[] pq;          // elements
	private int N;             // number of elements
	
	// set initial size of heap to hold size elements
	public OrderedArrayMaxPQ (int capacity)
	{
		pq = (Key[])(new Comparable[capacity]);
		N = 0;
	}
	
	public boolean isEmpty ()
	{
		return N == 0;
	}
	
	public int size ()
	{
		return N;
	}
	
	public Key max() // ***
	{
		return pq[N-1];
	}
	
	public Key delMax ()
	{
		return pq[--N];
	}
	
	public void insert (Key key)
	{
		int i = N - 1;
		while (i >= 0 && less(key, pq[i]))
		{
			pq[i + 1] = pq[i];
			i--;
		}
		pq[i + 1] = key;
		N++;
	}
	
	/***********************************************************************
	 * Helper functions.
	 **********************************************************************/
	private boolean less (Key v, Key w)
	{
		return (v.compareTo(w) < 0);
	}
	
	private void exch (int i, int j)
	{
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	/***********************************************************************
	 * Iterator ***
	 **********************************************************************/
	
	/**
	 * Returns an iterator that iterates over the keys on the priority queue in
	 * descending order. The iterator doesn't implement <tt>remove()</tt> since
	 * it's optional.
	 * 
	 * @return an iterator that iterates over the keys in descending order
	 */

	public Iterator<Key> iterator ()
	{
		return new OrderedArrayMaxPQIterator();
	}
	
	private class OrderedArrayMaxPQIterator implements Iterator<Key>
	{	
		private Key[] copy;      // elements
		private int current;
		private int numKeys;
		
		// add all items to copy of Comparable array
		// takes linear time
		public OrderedArrayMaxPQIterator ()
		{
			numKeys = N;
			copy = (Key[])new Comparable[numKeys];
			for (int i=0; i < numKeys; i++)
			{
				copy[i] = pq[i];
			}
			
			current = numKeys-1;
		}
		
		public boolean hasNext ()
		{
			return current >= 0;
		}
		
		public void remove ()
		{
			throw new UnsupportedOperationException();
		}
		
		public Key next ()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			
			return copy[current--];
		}
	}

	/***********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main (String[] args)
	{
//		OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
		
		PQ<String> pq = new OrderedArrayMaxPQ<String>(10); // ** plug into interface
		
		pq.insert("this");
		pq.insert("is");
		pq.insert("a");
		pq.insert("test");
		
		while (!pq.isEmpty())
			StdOut.println(pq.delMax());
	}
	
}

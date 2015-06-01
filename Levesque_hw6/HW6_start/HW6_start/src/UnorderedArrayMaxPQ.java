import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*************************************************************************
 * Compilation: javac UnorderedArrayMaxPQ.java Execution: java
 * UnorderedArrayMaxPQ
 * 
 * Priority queue implementation with an unsorted array.
 * 
 * Limitations ----------- - no array resizing - does not check for overflow or
 * underflow.
 * 
 *************************************************************************/

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> implements PQ<Key>
{
	private Key[] pq;      // elements
	private int N;         // number of elements
	
	// set initial size of array to hold size elements
	public UnorderedArrayMaxPQ (int capacity)
	{
		pq = (Key[])new Comparable[capacity];
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
	
	public void insert (Key x)
	{
		pq[N++] = x;
	}
	
	public Key max () // added to implement PQ<Key>
	{
		int max = 0;
		for (int i = 1; i < N; i++)
			if (less(max, i))
				max = i;
		
		return pq[max];
	}
	
	public Key delMax ()
	{
		int max = 0;
		for (int i = 1; i < N; i++)
			if (less(max, i))
				max = i;
		exch(max, N - 1);
		
		return pq[--N];
	}
	
	/***********************************************************************
	 * Helper functions.
	 **********************************************************************/
	private boolean less (int i, int j)
	{
		return (pq[i].compareTo(pq[j]) < 0);
	}
	
	private void exch (int i, int j)
	{
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}
	
	/***********************************************************************
	 * Iterator
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
		return new UnorderedArrayMaxPQIterator();
	}
	
	private class UnorderedArrayMaxPQIterator implements Iterator<Key>
	{	
		// create a sorted copy of 
		private Key[] copy;      // elements
		private int current;
		private int numKeys;
		
		// add all items to copy of heap
		// takes linear time since already in heap order so no keys move
		public UnorderedArrayMaxPQIterator ()
		{
			numKeys = N;
			copy = (Key[])new Comparable[numKeys];
			
			Arrays.sort(pq,0,N);
			
			for (int i=0; i < numKeys; i++)
			{
				copy[numKeys-i-1] = pq[i];
			}
			
			// Arrays.sort(copy);
			current = 0;
		}
		
		public boolean hasNext ()
		{
			return current < numKeys;
		}
		
		public void remove ()
		{
			throw new UnsupportedOperationException();
		}
		
		public Key next ()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			
			return copy[current++];
		}
	}

	/***********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main (String[] args)
	{
		// UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<String>(10);
		PQ<String> pq = new UnorderedArrayMaxPQ<String>(10);
		pq.insert("this");
		pq.insert("is");
		pq.insert("a");
		pq.insert("test");
		while (!pq.isEmpty())
			StdOut.println(pq.delMax());
	}
	
}

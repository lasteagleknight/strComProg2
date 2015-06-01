/**
 * Exam 2, Problem 1: implement a doubly linked list
 * 
 * @author eric
 * 
 */
public class DoubleLinked
{
	/*
	 * Add your instance variables (fields) here
	 */
	
	
	/**
	 * Nested class for representing each node in you doubly linked list: do NOT
	 * change
	 * 
	 * @author eric
	 * 
	 */
	public static class DNode
	{
		public String data;
		public DNode prev;
		public DNode next;
		
		public DNode ()
		{
			data = "";
			prev = next = null;
		}
	}
	
	/**
	 * initialize list to empty
	 */
	public DoubleLinked ()
	{
		
	}
	
	/**
	 * Insert a new DNode at the beginning of the list;
	 * 
	 * @param toAdd
	 */
	public void insertFirst (DNode toAdd)
	{
		
	}
	
	/**
	 * Insert a new DNode at the end of the list;
	 * 
	 * @param toAdd
	 */
	public void insertLast (DNode toAdd)
	{
		
	}
	
	/**
	 * Remove and return the DNode at the beginning of the list;
	 * 
	 * @return
	 */
	public DNode removeFirst ()
	{
		return null;
	}
	
	/**
	 * Remove and return the DNode at the end of the list;
	 * 
	 * @return
	 */
	public DNode removeLast ()
	{
		return null;
	}
	
	/**
	 * Insert a new DNode before a given DNode in the list;
	 * 
	 * @param toAdd
	 */
	public void insertBefore (DNode toAdd)
	{
		
	}
	
	/**
	 * Insert a new DNode after a given DNode in the list;
	 * 
	 * @param toAdd
	 */
	public void insertAfter (DNode toAdd)
	{
		
	}
	
	/**
	 * Remove and return the Nth DNode in the list, where the first node is
	 * N==1, and the last node is N==size().
	 * 
	 * @param N
	 * @return
	 */
	public DNode removeNth (int N)
	{
		return null;
	}
	
	/**
	 * Return the Nth DNode in a list, for N==1..size();
	 */
	public DNode getNth (int N)
	{
		return null;
	}
	
	/**
	 * Return the current number of DNode in the list.
	 */
	public int size ()
	{
		return 0;
	}
	
	/**
	 * Test client: add code if you wish, but it will
	 *  NOT be tested
	 *  
	 * @param args
	 */
	public static void main (String[] args)
	{
		DoubleLinked moxie = new DoubleLinked();
		
		StdOut.println (moxie.size());
	}
}

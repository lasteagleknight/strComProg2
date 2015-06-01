/**
 * Exam 2, Problem 1: implement a doubly linked list
 * 
 * Mod 1: fixed API for insertBefore(), insertAfter()
 * 
 * @author eric
 * 
 */
public class DoubleLinked
{
	private DNode head;
	private DNode tail;
	private int count;
	
	
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
		head = null;
		tail = null;
		count = 0;
	}
	
	/**
	 * Insert a new DNode at the beginning of the list;
	 * 
	 * @param toAdd
	 */
	public void insertFirst (DNode toAdd)
	{
		DNode holder = head;
		head = toAdd;
		head.next = holder;
		if(head.next != null){
			head.next.prev = head;
		}
		if(count == 0){
			tail = head;
		}
		count++;
	}
	
	/**
	 * Insert a new DNode at the end of the list;
	 * 
	 * @param toAdd
	 */
	public void insertLast (DNode toAdd)
	{
		if(tail != null){
		tail.next = toAdd;
		toAdd.prev = tail;
		}
		tail = toAdd;
		if(count == 0){
			head = tail;
		}	
		count++;
	}
	
	/**
	 * Remove and return the DNode at the beginning of the list;
	 * 
	 * @return
	 */
	public DNode removeFirst ()
	{
		DNode holder = head;
		head = head.next;
		count--;
		return holder;
	}
	
	/**
	 * Remove and return the DNode at the end of the list;
	 * 
	 * @return
	 */
	public DNode removeLast ()
	{
		DNode holder = tail;
		tail = tail.prev;
		count--;
		return holder;
	}
	
	/**
	 * Insert a new DNode before the given DNode in the list;
	 * 
	 * @param toAdd
	 */
	public void insertBefore (DNode beforeThis, DNode toAdd)
	{
		toAdd.next = beforeThis;
		toAdd.prev = beforeThis.prev;
		if(beforeThis.prev != null){
		beforeThis.prev.next = toAdd;
		}
		else{
			head = toAdd;
		}
		beforeThis.prev = toAdd;
		count++;
	}
	
	/**
	 * Insert a new DNode after the given DNode in the list;
	 * 
	 * @param toAdd
	 */
	public void insertAfter (DNode afterThis, DNode toAdd)
	{
		toAdd.next = afterThis.next;
		toAdd.prev = afterThis;
		if(afterThis.next != null){
		afterThis.next.prev = toAdd;
		}
		else{
			tail = toAdd;
		}
		afterThis.next = toAdd;
		count++;
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
		DNode holder = getNth(N);
		if(holder.prev != null && holder.next != null){
		holder.prev.next = holder.next;
		holder.next.prev = holder.prev;
		}
		else if(holder.prev == null && holder.next != null){
			head = holder.next;
			head.prev = null;
		}
		else if(holder.prev != null && holder.next == null){
			tail = holder.prev;
			tail.next = null;
		}
		count--;
		
		return holder;
	}
	
	/**
	 * Return the Nth DNode in a list, for N==1..size();
	 */
	public DNode getNth (int N)
	{
		DNode holder = head;
		if(N > 1){
			for(int i = 1; i < N; i++)
				holder = holder.next;
			return holder;
		}
		else 
			return holder;
		
	}
	
	/**
	 * Return the current number of DNode in the list.
	 */
	public int size ()
	{
		return count;
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

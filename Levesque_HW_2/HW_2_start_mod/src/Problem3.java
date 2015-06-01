import java.util.Arrays;

/*
 * Problem3:
 * 
 * 	Problem 1.2.9.  
 * 
 *  Note that the book's BinarySearch class has been renamed below to Problem3:
 *  	you should modify this by adding Counter instrumentation as described 
 *  	in the problem and in the Help Video.
 *  
 *  Also, a copy of the book's Counter class is included in the Eclipse project with these classes.
 */

public class Problem3
{
	/**
	 * This class should not be instantiated.
	 */
	private Problem3 ()
	{
	}
	
	/**
	 * Searches for the integer key in the sorted array a[].
	 * 
	 * @param key
	 *            the search key
	 * @param a
	 *            the array of integers, must be sorted in ascending order
	 * @return index of key in array a[] if present; -1 if not present
	 */
	public static int rank (int key, int[] a,Counter c)
	{
		
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi)
		{
			// Key is in a[lo..hi] or not present.
			c.increment();
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	
	/**
	 * Reads in a sequence of integers from the whitelist file, specified as a
	 * command-line argument. Reads in integers from standard input and prints
	 * to standard output those integers that do *not* appear in the file.
	 */
	public static void main (String[] args)
	{
		// read the integers from a file
		In in = new In(args[0]);
		int[] whitelist = in.readAllInts();
		Counter count = new Counter("key access");
		
		// sort the array
		Arrays.sort(whitelist);
		
		StdOut.println("Enter key enter -1 to end");
		
		// read integer key from standard input; print if not in whitelist
		while (!StdIn.isEmpty())
		{
			int key = StdIn.readInt();
			
			if(key == -1){
				StdOut.println(count.toString());
				break;
			}
			if (rank(key, whitelist, count) == -1)
				StdOut.println(key);
		}
		
	}
}

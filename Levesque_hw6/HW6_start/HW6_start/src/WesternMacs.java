/**
 * HW 6, Problem 1
 * 
 * Same as BottomM from Class 9, though reworked to add PQ<Key>
 * 	interface. 
 * 
 * Modify this so it finds and prints
 *  Mth western-most MacDonalds stores, with data read from 
 *  mcdonalds.csv, a comma-separated values file containing
 *  all McDonalds stores in the U.S.
 *  
 */
public class WesternMacs
{
	// This class should not be instantiated.
	private WesternMacs ()
	{
	}	
	/**
	 * Reads int M and file name from command line, then opens and reads 
	 * sequence of (latitude,longitude) pairs (sequence number counter)
	 * creates Location objects from each line and adds to priority queue
	 * pq, keeping only "lowest" M values.
	 * 
	 * When done, prints M smallest (most eastern) locations in descending order.
	 */
	public static void main (String[] args)
	{
		int M = Integer.parseInt(args[0]);
		In file = new In("mcdonalds.csv"); // you can 'hard-code' name as "mcdonalds.csv"
		
	// The following demonstrates the use of a Java interface, PQ<Key>:
	//	the book's PQ implementations have been altered to implement this
	//	interface, so that each can be "plugged into" a PQ<Key> ref variable,
	//	for use below.
		
	//	MaxPQ<Location> pq = new MaxPQ<Location>(M + 1);  // original TopM code
		
		MinPQ<Location> pq = new MinPQ<Location>(M + 1);  
	//	PQ<Location> pq = new UnorderedArrayMaxPQ<Location>(M + 1);  
	//	PQ<Location> pq = new OrderedArrayMaxPQ<Location>(M + 1);  

		int counter = 0;
		while (file.hasNextLine())
		{
			// replace Location with McDonalds, in what follows
			
			// Create a Location entry from the next space-delimited line 
			
			String line = file.readLine();
			String[] splitLine = line.split(",");
			
			double latitude = Double.parseDouble(splitLine[0]);
			double longitude = Double.parseDouble(splitLine[1]);
			
			Location location = new Location(latitude,longitude,counter++);
			
			// Add new Location to PQ
			
			pq.insert(location);
			
			// remove M if M+1 entries on the PQ
			if (pq.size() > M)
				pq.delMin();
			
		}   // bottom M entries are on the PQ
		
		// print entries on PQ in reverse order
		Stack<Location> stack = new Stack<Location>();
		
		for (Location location : pq) // assumes pq is Iterable!
			stack.push(location);
		
		for (Location location : stack)
			StdOut.println(location);
	}
}

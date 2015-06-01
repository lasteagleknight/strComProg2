/**
 * HW 6, Problem 1 example
 * 
 * Same as TopM from book, modified to find the Mth least elements
 * using max-oriented heap MaxPQ.
 * 
 * Class Location represents (long,lat) pairs, each for a U.S. city
 * of >500 population.
 * 
 * Data from usa3509.txt are lines of such pairs; file is included
 * in project.
 * 
 */
public class BottomM
{
	// This class should not be instantiated.
	private BottomM ()
	{
	}	
	/**
	 * Reads int M, command line, then opens and reads usa13509.txt whose lines
	 * are sequence of (latitude,longitude) pairs (sequence number counter),
	 * creating Location objects from each line, then adding to priority queue
	 * pq, keeping only "lowest" (most eastern) M longitude values.
	 * 
	 * When done, prints M smallest (most eastern) locations in descending order.
	 */
	public static void main (String[] args)
	{
		int M = Integer.parseInt(args[0]);		
		In file = new In("usa13509.txt");
		
	// The following demonstrates the use of a Java interface, PQ<Key>:
	//	the book's PQ implementations have been altered to implement this
	//	interface, so that each can be "plugged into" a PQ<Key> ref variable,
	//	for use below.
		
	//	MaxPQ<Location> pq = new MaxPQ<Location>(M + 1);  // original TopM code
		
		PQ<Location> pq = new MaxPQ<Location>(M + 1);  
	//	PQ<Location> pq = new UnorderedArrayMaxPQ<Location>(M + 1);  
	//	PQ<Location> pq = new OrderedArrayMaxPQ<Location>(M + 1);  

		int counter = 0;
		while (file.hasNextLine())
		{
			// Create a Location entry from the next space-delimited line 
			
			String line = file.readLine();
			String[] splitLine = line.split(" ");
			
			double latitude = Double.parseDouble(splitLine[0]);
			double longitude = Double.parseDouble(splitLine[1]);
			
			Location location = new Location(latitude,longitude,counter++);
			
			// Add new Location to PQ
			
			pq.insert(location);
			
			// remove M if M+1 entries on the PQ
			if (pq.size() > M)
				pq.delMax();
			
		}   // bottom M entries are on the PQ
		
		// print entries on PQ in reverse order
		Stack<Location> stack = new Stack<Location>();
		
		for (Location location : pq) // assumes pq is Iterable!
			stack.push(location);
		
		for (Location location : stack)
			StdOut.println(location);
	}
}

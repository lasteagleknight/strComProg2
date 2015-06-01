/**
 * HW 6, Problem 7 (Extra Credit)
 * 
 * Same as WesternMacs (starting for Problem 1).
 * 
 * Modify so it finds and prints the M MacDonalds stores
 *	closest to the 45th parallel (of latitude).
 *
 */
public class McDonalds45
{
	// This class should not be instantiated.
	private McDonalds45 ()
	{
	}	

	public static void main (String[] args)
	{
		int M = Integer.parseInt(args[0]);
		In file = new In("mcdonalds.csv");
		
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
			String[] splitLine = line.split(",");
			
			// replace the following with McDonalds
			
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

/**
 *  Same as TopM from book, modified to find the Mth least elements
 */
public class BottomM {   

    // This class should not be instantiated.
    private BottomM() { }

    /**
     *  Reads a sequence of (latitude,longitude) pairs with seq number seq
     *  from standard input; takes a command-line integer M; prints to 
     *  standard output the M smallest (most eastern)
     *  locations in descending order.
     */
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        In file = new In(args[1]);
        MaxPQ<Location> pq = new MaxPQ<Location>(M+1);  // we'll try two other implementations
        int counter = 0;
        while (file.hasNextLine()) {
            // Create an entry from the next line and put on the PQ. 
            String line = file.readLine();
            String[] splitLine = line.split(" ");
            Location location = new Location(Double.parseDouble(splitLine[0]),
            					             Double.parseDouble(splitLine[1]),
            					             counter++);
            pq.insert(location); 

            // remove M if M+1 entries on the PQ
            if (pq.size() > M) 
                pq.delMax();
        }   // bottom M entries are on the PQ

        // print entries on PQ in reverse order
        Stack<Location> stack = new Stack<Location>();
        for (Location location : pq)
            stack.push(location);
        for (Location location : stack)
            StdOut.println(location);
    } 
} 


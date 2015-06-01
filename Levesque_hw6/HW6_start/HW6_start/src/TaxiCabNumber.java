/**
 * Hw 6, Problem 2
 * 
 * Problem 2.4.25 in textbook
 * 
 * 
 */
public class TaxiCabNumber
{
	public static final int UPPER_BOUND = 1000;
	
	// Use the following inner class to represent (i**3+j**3,i,j)
	
	public static class CubeSum implements Comparable<CubeSum>
	{
		public long IcubedPlusJcubed;
		public int I;
		public int J;
		
		public CubeSum (int i, int j)
		{
			I = i;
			J = j;
			long longI = i;
			long longJ = j;
			IcubedPlusJcubed = longI*longI*longI + longJ*longJ*longJ;
		}
		public String toString()
		{
			return ("(" + IcubedPlusJcubed + "," + I + "," + J + ")");
		}
		public int compareTo(CubeSum obj)
		{
			if (IcubedPlusJcubed > obj.IcubedPlusJcubed)
				return +1;
			else if (IcubedPlusJcubed < obj.IcubedPlusJcubed)
				return -1;
			else
				return 0;		
		}
	}
	
	public static void main (String[] args)
	{
		
	// Print out all distinct ints a, b, c, d
	//	such that a**3+b**3 = c**3+d**3
	// Use MaxPQ<CubeSum> and the book's algorithm
		
		MinPQ<CubeSum> pq = new MinPQ<CubeSum>();
		
		 
		CubeSum b = new CubeSum(1,0);
		CubeSum c = new CubeSum(1,0);
		for(int z = 0; z < 20; z++ ){
			for(int i = 0;  i < 1000; i++ ){
		for (int j=0;j< 1000;j++){
			CubeSum a = new CubeSum(i,j);
			pq.insert(a);
			
			if (pq.size() > z)
				
				b = pq.delMin();
				if(a.compareTo(b) == 0 && (a.I != b.I && a.J != b.J)){
					StdOut.println("first" +a);
					StdOut.println("second" +b);
					StdOut.println("third" +c);
				}
				c = a;
		}
		}
		}
		
	}
		
	
	
}

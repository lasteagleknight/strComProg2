import java.util.Arrays;

/**
 * HW 4, Problem 3
 * 
 * @author eric
 *
 */
public class ClosestPair
{
	
	public static void main (String[] args)
	{
		double[] test1 = new double[] { 1.0, 2.0, 3.0 };
		double[] test2 = new double[] { 1.0, 1.5, 3.0, 47.0 };
		
		printClosestPair(test1); // should print (0,1) - 1.0 OR (1,2) - 1.0
		printClosestPair(test2); // should print (0,1) - 0.5
		
	}
	
	public static void printClosestPair(double[] arr)
	{
		double a = Double.MAX_VALUE, 
				b = Double.MIN_VALUE, dist = Double.MAX_VALUE;
		
		Arrays.sort(arr);
		
		for(int c = 1; c < arr.length; c++){
			if(dist > Math.abs(arr[c]-arr[c-1])){
				dist = Math.abs(arr[c]-arr[c-1]);
				a = arr[c-1];
				b = arr[c];
			}
		}
		
		StdOut.printf("(%.0f, %.0f) - %.2f", a,b,dist);
		StdOut.println();
	}
	
}

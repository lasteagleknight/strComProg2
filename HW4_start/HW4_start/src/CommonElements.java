import java.util.Arrays;

/**
 * HW 4, Problem 2
 * 
 * @author eric
 *
 */
public class CommonElements
{
	
	public static void main (String[] args)
	{
		int[] a1 = { 1, 2, 3, 4, 5, 6, 8};
		int[] a2 = { 0, 1, 3, 4, 7, 8 };
		
		printCommon(a1, a2);	
	}
	
	/**
	 * Assume a1, a2 sorted in ascending order:
	 * 
	 * Print each integer once that  occurs in *both* a1, a2
	 * 
	 * @param a1
	 * @param a2
	 */
	public static void printCommon (int[] a1, int[] a2)
	{
		Arrays.sort(a1);
		Arrays.sort(a2);
		int y = 0;
		
		for(int z = 0; z < a1.length;z++){
			while(a1[z] > a2[y] && y < a2.length){
				y++;
			}
			if(a1[z] == a2[y])
				StdOut.println(a1[z]);
		}
		
	}
}

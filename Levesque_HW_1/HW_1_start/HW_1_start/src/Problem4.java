import java.util.Arrays;

/*
 * Problem 4 is written in BinarySearch and this method using the removeduplicates class. I have set up a test
 * method in Problem4Test
 */

public class Problem4 {
	
	public static int[] removeduplicates(int[] toSort){
		int numbKeys = 0;//keeps track of the new array length
		int prev = Integer.MIN_VALUE;// keeps track of old numbers
		int[] result1 = new int[toSort.length];// array to pass sorted array to
		
		Arrays.sort(toSort);
		
		for(int i = 0; i < toSort.length; i++){// sorting loop
			if(toSort[i] != prev){// if numb does not equal prev number will go on
				result1[numbKeys] = toSort[i];
				prev = toSort[i];// remembers number placed
				numbKeys++;
			}
		}
		int[] result = new int[numbKeys];// resets array length.
		for(int a = 0; a < result.length; a++ ){
			result[a] = result1[a];
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

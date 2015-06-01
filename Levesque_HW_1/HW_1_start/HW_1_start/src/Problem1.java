
public class Problem1 {
	
	public static int[] histogram(int[] array, int M){
		
		int[] result = new int[M]; //allocate new array of length M
	
		
		for(int a = 0; a < array.length; a++) //'for-each' iteration over an array
		{
			
			if(array[a] < M){// checks to see if number is in range
			result[array[a]]++;//adds number to incrementing array
			}

		}
		
		
		return result;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

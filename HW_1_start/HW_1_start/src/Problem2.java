
public class Problem2 {

	public static void main(String[] args) {
		
		int nextFree = 0;
		String[] names = new String[20];
		int[] firstInts = new int[20];
		int[] secondInts = new int[20];
		
		intro();
	
		while(!StdIn.isEmpty() && nextFree < 20)
		{
			String name = StdIn.readString();
			
			int int1 = StdIn.readInt();
			int int2 = StdIn.readInt();
				
			names[nextFree] = name;
			firstInts[nextFree] = int1;
			secondInts[nextFree] = int2;
		
			StdOut.print(name +" ");
			StdOut.print(int1 +" ");
			StdOut.println(int2 +" ");
			
			nextFree++;
			
		}
		
		for(int a = 0; a < nextFree; a++){
			StdOut.printf("%-6s %6d %6d %7.3f\n", names[a], firstInts[a], secondInts[a], (0.0+firstInts[a])/secondInts[a]);
		}

	}
	
	static void intro(){
		StdOut.println("Enter name and two numbers with spaces in between");
	}
}


/*
 * Problem2:
 * 
 * Problem 1.2.6 from textbook.
 * 
 * Note that the book's web site outline for 1.2 has a one-line solution to this problem, but
 *  the help video discusses alternate ways of solving this.
 * 
 * @author eric
 *
 */
public class Problem2 
{

	public static void main(String[] args) 
	{
		StdOut.println("Enter a string:");
		String s = StdIn.readLine();
		
		StdOut.println("Enter comparable string:");
		String t = StdIn.readLine();
		
		String curcShift = t;
		
		for(int i = 0;i < s.length();i++){
			
			curcShift = curcShift.substring(1) + curcShift.substring(0,1);
			StdOut.println(curcShift);
			if(s.equalsIgnoreCase(curcShift)){
				StdOut.printf("%s is a circular rotation of %s", s,t);
				break;	
			}
			else if(i == s.length()-1){
				StdOut.printf("%s is not a circular rotation of %s", s,t);
			}
		}
		
		//StdOut.println(t);

	}

}

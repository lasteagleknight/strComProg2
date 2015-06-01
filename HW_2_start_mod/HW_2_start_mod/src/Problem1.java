import java.awt.Color;



public class Problem1 
{

	
	public static void main(String[] args) 
	{
		StdOut.println("Enter an integer N:");
		
		int N = Integer.parseInt(StdIn.readString());
		double smallestLength = Double.MAX_VALUE;
		int point1 = 0, point2 = 0;
		
		StdDraw.setXscale(0.0, 1.0);
		StdDraw.setYscale(0.0, 1.0);
		StdDraw.setPenRadius(.005);
		
		Point2D[] allPoints = new Point2D[N];
		
		for(int a = 0; a < N; a++){//set up points for N
			allPoints[a] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
			allPoints[a].draw();
		}
		
		for(int b = 0; b < N; b++){//review all points to determine the shortest length
			for(int c = b; c < N; c++){
				if(b != c){// checks to see if they are the same point
					if(smallestLength > allPoints[b].distanceTo(allPoints[c])){
						
						smallestLength = allPoints[b].distanceTo(allPoints[c]);// sets length if its the smallest
						point1 = b;// remember point
						point2 = c;// remember point
						
					}//end check if smallest point	
				}// end of check if both ints are the same
			}//end of c statement
		}//end b for statement
		
		StdDraw.setPenColor(Color.RED);//sets color to see points
		allPoints[point1].draw();
		allPoints[point2].draw();
		allPoints[point1].drawTo(allPoints[point2]);
		StdOut.printf("The shortest distance is %f between point %d and %d", smallestLength, point1, point2);
		
	}//end main
}// end class problem1

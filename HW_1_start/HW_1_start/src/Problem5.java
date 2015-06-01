
/*
 * I tested this program three times 300842, 510758, 313390 are the rolls before both sets match.
 */

import java.awt.geom.Arc2D.Double;
import java.util.Random;

public class Problem5 {
	
		
		static Random rand = new Random();
		private static int NUMBER_OF_ROLLS = 0;
		static int SIDES = 6;
		static double[] frequency = new double[2*SIDES+1];
		
		
		public static void main(String[]args){
			
			do{
				
				int roll = twoDieRoll();
				++frequency[roll]; 
			
			NUMBER_OF_ROLLS++;
			
			if(NUMBER_OF_ROLLS%1000 == 0){
				System.out.println("a");
				barChart(result());
				System.out.println("b");
				barChart(probablity());
				System.out.println(NUMBER_OF_ROLLS);
				
			}
			
			}while(!equals());
			
			
			
			barChart(frequency);
			StdOut.printf("The numbers of rolls are %d", NUMBER_OF_ROLLS);
		
		}
		
		public static int dice(){
			int dice = 1+ rand.nextInt(6);
			return dice;
		}
		
		public static int twoDieRoll(){
			int die1 = dice();
			int die2 = dice();
			int twoDie = die1 + die2;
			return twoDie;
		}
		
		public static void barChart(double[] array2){
			
			for(int count = 0; count < array2.length; count++){
				System.out.printf("Number %2d: %4f\n", count, (array2[count]));
				
			}
			
		}
		
		public static double[] probablity(){
			 
			double[] dist = new double[2*SIDES+1];
			
			for (int i = 1; i <= SIDES; i++){
				for(int j = 1; j <= SIDES; j++){
					dist[i+j]++;
				}
			}
			for(int k = 2; k <= 2*SIDES; k++){
				dist[k] /= 36.0;
				dist[k] = (double)Math.round(dist[k] * 1000) / 1000;
			}
			
			return dist;
		}
		
		public static double[] result(){
			double[] array = new double[frequency.length];
			
			for(int count = 0; count < array.length; count++){
				array[count] = frequency[count]/NUMBER_OF_ROLLS;
				array[count] = (double)Math.round(array[count] * 1000) / 1000;
			}
			
			return array;
		}
		
		public static boolean equals(){
			boolean a = true;
			for(int count = 0; count < result().length; count++){
				if(result()[count] != probablity()[count]){
					a = false;
				}	
			}
			return a;
		}

}




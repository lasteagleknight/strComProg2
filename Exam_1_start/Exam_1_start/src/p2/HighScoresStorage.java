package p2;

/**
 * Implementing class for HighScores Java interface.
 * 
 * Exam 1, Problem 2
 * CSci 2002.91
 * Spring 2014
 * 
 * @author eric
 *
 */

public class HighScoresStorage implements HighScores // => all methods
{
	
	int[] score = new int[10];
	
	/**
	 * Initializes top-ten scores, initially all -1
	 */
	
	public HighScoresStorage ()
	{
		for(int i = 0; i < score.length;i++){
			score[i] = -1;
		}
	}
	
	/**
	 * If newScore qualifies for top 10 scores and is >= 0: remove the lowest
	 * score, add newScore to the list, and return true.
	 * 
	 * Otherwise: do nothing and return false.
	 * 
	 */
	
	public boolean addScore (int newScore)
	{
		for(int i = 0; i < score.length;i++){
			if(score[i] < newScore){
				for(; i < score.length;i++){
					int temp = score[i];
					score[i] = newScore;
					newScore = temp;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return nth largest score stored so far, 
	 * 	for 1 <= n <= 10 from current top 10 scores.
	 * 
	 * If n <= 0 or n > 10, return -1.
	 * 
	 */
	
	public int nthHighest (int n)
	{
		if(n > 0 && n <= 10){
			return getTopTen()[n+1];
		}
		return -1;
	}
	
	/**
	 * Return a length-10 int array containing the top 10 scores, from highest
	 * to lowest
	 * 
	 */
	
	public int[] getTopTen ()
	{
		
		return score;
	}
	
	public static void main (String[] args)
	{
		// use this method to implement your own test client,
		//	but remember that only the above methods will be unit-tested
		
	}
	
}

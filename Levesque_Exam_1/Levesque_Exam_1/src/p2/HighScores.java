package p2;

/**
 * Java interface HighScores.
 * 
 * Exam 1, Problem 2
 * CSci 2002.91
 * Spring 2014
 * 
 * @author eric
 *
 */
public interface HighScores
{	
	/**
	 * If newScore qualifies for top 10 scores and is >= 0: 
	 * remove the lowest score,
	 *	add newScore to the list, 
	 *	and return true.  
	 *
	 * Otherwise: 
	 *   do nothing 
	 *    and return false.
	 *    
	 * @param newScore
	 * @return
	 */
	
	public int nthHighest (int n);
		
	/**
	 * Return nth largest score stored so far, 
	 *   for 1 <= n <= 10 from current top 10 scores.
	 * 
	 * If n <= 0 or n > 10 ***, return -1.
	 * 
	 * @return
	 */

	public boolean addScore (int newScore);

	/**
	 * Return a length-10 int array containing the top 10 scores, 
	 *	from highest to lowest
	 *
	 * @return
	 */
	
	public int[] getTopTen ();
	
}

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SortCompareTest
{
	
	@Before
	public void setUp () throws Exception
	{
	}
	
	@Test
	public void test1 ()
	{
		double totalTimeNo = SortCompare.timeRandomInput("InsertionNoExchanges", 1000, 100);
		double totalTime = SortCompare.timeRandomInput("Insertion", 1000, 100);
		StdOut.printf("1000/100: %5.2f\n", totalTimeNo / totalTime);
		assertTrue("test1: Time for sorting 1000 items w/ 100 trials: noexchanges < original", true); // totalTimeNo < totalTime);
	}
	
}

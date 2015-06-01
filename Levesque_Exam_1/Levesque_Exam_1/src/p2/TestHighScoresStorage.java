/*  
 * Score: 12/15
 */

package p2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class TestHighScoresStorage
{
	private HighScoresStorage storage;
	private boolean boolResult;
	
	@Before
	public void setUp () throws Exception
	{
		storage = new HighScoresStorage();
	}
	
	@Test
	public void testInitial1 ()  // #1 ***
	{
		int[] topTen = storage.getTopTen();
		
		for (int sc : topTen)
		{
			assertEquals(sc, -1); // all should initialize to -1
		}
	}
	
	@Test
	public void testNthHighest1 () // #2
	{
		for (int i = 1; i <= 10; i++)
		{
			assertEquals(storage.nthHighest(i), -1); // all are -1
		}
	}
	
	@Test
	public void testAddScore1 ()  // #3
	{
		assertTrue(storage.addScore(0));
		
		int[] topTen = storage.getTopTen();
		
		assertEquals(topTen[0], 0); // 0 is new top score
		
		for (int i = 1; i < 10; i++)
		{
			assertEquals(topTen[i], -1); // remaining are -1
		}
	}
	
	@Test
	public void testAddScore2 () // #4 ***
	{
		// add 9 scores, leaving one default -1
		
		storage.addScore(1);
		storage.addScore(2);
		storage.addScore(3);
		storage.addScore(4);
		storage.addScore(5);
		storage.addScore(6);
		storage.addScore(7);
		storage.addScore(8);
		storage.addScore(9);
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 9; i++)
		{
			assertEquals(topTen[i], 9 - i);
		}
		
		assertEquals(topTen[9], -1);
	}
	
	@Test
	public void testAddScore3 () // #5
	{
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(0);
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 8; i++)
		{
			assertEquals(topTen[i], 1);
		}
		
		assertEquals(topTen[8], 0);
		assertEquals(topTen[9], -1);
	}
	
	@Test
	public void testAddScore4 () // #6 ***
	{
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		storage.addScore(1);
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 10; i++)
		{
			assertEquals(topTen[i], 1);
		}
	}
	
	@Test
	public void testAddScore5 () // #7 ***
	{
		storage.addScore(4);
		storage.addScore(1);
		storage.addScore(3);
		storage.addScore(2);
		storage.addScore(9);
		storage.addScore(8);
		storage.addScore(5);
		storage.addScore(6);
		storage.addScore(7);
		storage.addScore(0);
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 10; i++)
		{
			assertEquals(topTen[i], 9 - i);
		}
	}
	
	@Test
	public void testTopTenDescending () // #8
	{
		// add random scores in range 0..100,
		// verify stored in decreasing (>=) order
		
		Random r = new Random();
		
		for (int i = 0; i < 10; i++)
			storage.addScore(r.nextInt(100));
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 9; i++)
		{
			assertTrue(topTen[i] >= topTen[i + 1]);
		}
	}
	
	@Test
	public void testAddScore7 () // #9
	{
		// try to add illegal scores (negatives)
		// => should return false, not changing defaults
		
		storage.addScore(4);
		storage.addScore(1);
		storage.addScore(3);
		storage.addScore(2);
		storage.addScore(9);
		storage.addScore(8);
		storage.addScore(5);
		storage.addScore(6);
		storage.addScore(7);
		storage.addScore(0);
		
		for (int i = 1; i <= 10; i++)
		{
			assertFalse(storage.addScore(-i));
		}
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 10; i++)
		{
			assertEquals(topTen[i], 9-i);
		}
	}
	
	@Test
	public void testAddScore8 () // #10
	{
		// try to add illegal scores (negatives)
		// => should return false, not changing defaults
		
		for (int i = 1; i <= 10; i++)
		{
			assertFalse(storage.addScore(-i));
		}
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 10; i++)
		{
			assertEquals(topTen[i], -1);
		}
	}
	
	@Test
	public void testAddScore9 () // #11 ***
	{
		// add 10 valid identical
		// then another different valid score
		
		for (int i = 0; i < 9; i++)
		{
			storage.addScore(47);
		}
		
		storage.addScore(7);
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 9; i++)
		{
			assertEquals(topTen[i], 47);
		}
		
		assertEquals(topTen[9], 7);
		
	}
	
	@Test
	public void testAddScore10 () // #12
	{
		// add 0..9, omitting 5
		
		storage.addScore(4);
		storage.addScore(1);
		storage.addScore(3);
		storage.addScore(2);
		storage.addScore(9);
		storage.addScore(8);
		storage.addScore(6);
		storage.addScore(7);
		storage.addScore(0);
		
		int[] topTen = storage.getTopTen();
		
		for (int i = 0; i < 4; i++) // 9,8,7,6
		{
			assertEquals(topTen[i], 9 - i);
		}
		
		for (int i = 4; i < 9; i++) // 4,3,2,1,0
		{
			assertEquals(topTen[i], 8 - i);
		}
		
		assertEquals(topTen[9], -1);
	}
	
	@Test
	public void testNthHighest2 () // #13
	{
		for (int i = 0; i < 10; i++)
		{
			storage.addScore(i); // 9,8,...1,0
		}
		
		for (int i = 1; i <= 10; i++)
		{
			assertEquals(storage.nthHighest(i), 10 - i); // all are -1
		}
	}
	
	@Test
	public void testNthHighest3 () // #14 ***
	{
		// invalid args=> return -1
		
		assertEquals(storage.nthHighest(-1), -1);
		assertEquals(storage.nthHighest(11), -1);
		assertEquals(storage.nthHighest(0), -1);
	}
	
	@Test
	public void testNthHighest4 () // #15
	{
		for (int i = 0; i < 10; i++)
		{
			storage.addScore(i); // 9,8,...1,0
		}
		
		assertEquals(storage.nthHighest(-1), -1);
		assertEquals(storage.nthHighest(11), -1);
		assertEquals(storage.nthHighest(0), -1);
		
		for (int i = 1; i <= 10; i++)
		{
			assertEquals(storage.nthHighest(i), 10 - i);
		}
	}
	
//	@Test
//	public void testTopTen1 () // #16 (extras)
//	{
//		for (int i = 0; i < 10; i++)
//		{
//			assertTrue(storage.addScore(i)); // 9,8,...1,0
//		}
//		
//		int[] topTen = storage.getTopTen();
//		
//		for (int i = 0; i < 10; i++)
//		{
//			assertEquals(topTen[i], 9 - i);
//		}
//	}
//	
//	@Test
//	public void testTopTen2 () // #17 (extras)
//	{
//		for (int i = 10; i > 0; i--)
//		{
//			assertTrue(storage.addScore(i)); // 9,8,...1,0
//		}
//		
//		int[] topTen = storage.getTopTen();
//		
//		for (int i = 0; i < 10; i++)
//		{
//			assertEquals(topTen[i], 10 - i);
//		}
//	}
//	
//	@Test
//	public void testTopTen3 () // #18 (extras)
//	{
//		for (int i = 0; i < 20; i++)
//		{
//			assertTrue(storage.addScore(i)); // 
//		}
//		
//		int[] topTen = storage.getTopTen();
//		
//		for (int i = 0; i < 10; i++)
//		{
//			assertEquals(topTen[i], 19-i);
//		}
//	}
//	

}

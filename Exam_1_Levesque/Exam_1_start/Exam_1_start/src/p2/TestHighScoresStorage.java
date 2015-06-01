// 6 JUnit tests for Exam 1, Problem 2

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
	public void testInitial1()
	{
		int[] topTen = storage.getTopTen();
		
		for (int sc : topTen)
		{
			assertEquals(sc, -1); // all should initialize to -1
		}
	}
	
	@Test
	public void testAddScore2()
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
			StdOut.println(topTen[i]);
			assertEquals(topTen[i], 9 - i);
		}
		
		assertEquals(topTen[9], -1);
		StdOut.println(topTen[9]);
	}
	
	@Test
	public void testAddScore4()
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
	public void testAddScore5()
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
	public void testAddScore9()
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
	public void testNthHighest3()
	{
		// invalid args=> return -1
		
		assertEquals(storage.nthHighest(-1), -1);
		assertEquals(storage.nthHighest(11), -1);
		assertEquals(storage.nthHighest(0), -1);
	}	
}

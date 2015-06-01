import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BinarySearchTest {

	private int[] test1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private int[] test2 = { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 };
	private int[] test3 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, -47 };
	private int[] toTest;

	public int[] makeSortedCopy(int[] original) {
		
		int[] copy = Arrays.copyOf(original, original.length);
		
		Arrays.sort(copy);
		
		return copy;
	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testRank1() {
		toTest = makeSortedCopy(test1);
		int rank = BinarySearch.rank(0, toTest);
		StdOut.println("rank is: " + rank);
		assertEquals(rank, 0);
	}

	@Test
	public void testRank2() {
		toTest = makeSortedCopy(test2);
		int rank = BinarySearch.rank(10, toTest);
		StdOut.println("rank is: " + rank);
		assertEquals(rank, 9);
	}

	@Test
	public void testRank3() {
		toTest = makeSortedCopy(test3);
		int rank = BinarySearch.rank(-47, toTest);
		StdOut.println("rank is: " + rank);
		assertEquals(rank, 0);
	}

	@Test
	public void testRank4() {
		int[] localCopy = { 1, 2, 2, 2, 2, 2, 47};
		int rank = BinarySearch.rank(2, localCopy);
		StdOut.println("rank is: " + rank);
		assertEquals(rank,3);
		rank = BinarySearch.rank(-47, localCopy);
		assertEquals(rank, -1);
		
	}

	@Test
	public void testRank5() {
		fail("Not yet implemented");
	}

}

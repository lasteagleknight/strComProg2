import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class Problem1Test {

	private int[] toTest;
	private int[] result;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHistogram1() {
		toTest = new int[] {1,2,1,3,1,1};
		result = Problem1.histogram(toTest, 4);
		
		for(int j = 0;j < result.length;j++){
			System.out.print(result[j] +" ");
		}
		System.out.println();
		
		assertArrayEquals(new int[]{0,4,1,1}, result);
	}
	
	@Test
	public void testHistogram2() {
		toTest = new int[] {1,1,1,1,1,1};
		result = Problem1.histogram(toTest, 4);
		
		for(int j = 0;j < result.length;j++){
			System.out.print(result[j] +" ");
		}
		
		System.out.println();
		
		assertArrayEquals(new int[]{0,6,0,0}, result);
	}
	
	@Test
	public void testHistogram3() {
		toTest = new int[] {1,1,1,2,3,5,3,2,2,0};
		result = Problem1.histogram(toTest, 7);
		
		for(int j = 0;j < result.length;j++){
			System.out.print(result[j] +" ");
		}
		
		System.out.println();
		
		assertArrayEquals(new int[]{1,3,3,2,0,1,0}, result);
	}
	@Test
	public void testHistogram4() {
		toTest = new int[] {0, 22, 3, 4, 5,4,1,6,6,7,8,3,6,3};
		result = Problem1.histogram(toTest, 7);
		
		for(int j = 0;j < result.length;j++){
			System.out.print(result[j] +" ");
		}
		
		System.out.println();
		
		assertArrayEquals(new int[]{1,1,0,3,2,1,3}, result);
	}
	
	@Test
	public void testHistogram5() {
		toTest = new int[] {10,10,10,2,3,5,3,2,2,0,3,3,3,3};
		result = Problem1.histogram(toTest, 11);
		
		for(int j = 0;j < result.length;j++){
			System.out.print(result[j] +" ");
		}
		
		System.out.println();
		
		assertArrayEquals(new int[]{1,0,3,6,0,1,0,0,0,0,3}, result);
	}

}

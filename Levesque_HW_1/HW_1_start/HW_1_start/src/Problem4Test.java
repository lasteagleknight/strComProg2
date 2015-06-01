import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class Problem4Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int[] test ={1,1,1, 2,4,5,5,5,7,7,7,8,9,9,};
		int[] result = Problem4.removeduplicates(test);
		
		for(int j = 0;j < result.length;j++){
			System.out.print(result[j] +" ");
		}
		
		assertArrayEquals(new int[]{1,2,4,5,7,8,9}, result);
	}

}

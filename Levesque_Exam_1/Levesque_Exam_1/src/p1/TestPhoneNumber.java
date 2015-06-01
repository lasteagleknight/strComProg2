/*  
 * Score: 14/15
 */

package p1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPhoneNumber
{
	private PhoneNumber pnumToTest;
	
	@Before
	public void setUp () throws Exception
	{
	}
	
	@Test
	public void testDefaultConstructor () // #1 ***
	{
		pnumToTest = new PhoneNumber();
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor1 () // #2
	{
		pnumToTest = new PhoneNumber("800-555-1212");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor2 () // #3 ***
	{
		pnumToTest = new PhoneNumber("651-474-4747");
		
		assertEquals(pnumToTest.getAreaCode(), "651");
		assertEquals(pnumToTest.getPrefix(), "474");
		assertEquals(pnumToTest.getSuffix(), "4747");
	}
	
	@Test
	public void testStringConstructor3 () // #4
	{
		// invalid chars => default #
		
		pnumToTest = new PhoneNumber("XYZ-474-4747");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor4 () // #5
	{
		// invalid chars => default #
		
		pnumToTest = new PhoneNumber("800-XYZ-4747");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor5 () // #6 
	{
		// invalid chars => default #
		
		pnumToTest = new PhoneNumber("800-474-ABCD");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor6 () // #7 ***
	{
		// invalid chars => default #
		
		pnumToTest = new PhoneNumber("800474ABCD");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor7 () // #8
	{
		// invalid chars => default #
		
		pnumToTest = new PhoneNumber("800=474=ABCD");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor8 () // #9
	{
		// invalid chars => default #
		
		pnumToTest = new PhoneNumber("474-4747");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	

	@Test
	public void testGetters () // #10 ***
	{
		pnumToTest = new PhoneNumber();
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
		
		pnumToTest = new PhoneNumber("805-654-4747");
		
		assertEquals(pnumToTest.getAreaCode(), "805");
		assertEquals(pnumToTest.getPrefix(), "654");
		assertEquals(pnumToTest.getSuffix(), "4747");
	}
	
	@Test
	public void testIsTollFree () // #11
	{
		pnumToTest = new PhoneNumber();
		assertTrue(pnumToTest.isTollFree());
		
		pnumToTest = new PhoneNumber("866-555-4747");
		assertTrue(pnumToTest.isTollFree());
		
		pnumToTest = new PhoneNumber("877-555-4747");
		assertTrue(pnumToTest.isTollFree());
		
		pnumToTest = new PhoneNumber("880-555-4747");
		assertTrue(pnumToTest.isTollFree());
		
		pnumToTest = new PhoneNumber("881-555-4747");
		assertTrue(pnumToTest.isTollFree());
		
		pnumToTest = new PhoneNumber("882-555-4747");
		assertTrue(pnumToTest.isTollFree());
		
		pnumToTest = new PhoneNumber("888-555-4747");
		assertTrue(pnumToTest.isTollFree());
		
		pnumToTest = new PhoneNumber("987-555-4747");
		assertFalse(pnumToTest.isTollFree());

		pnumToTest = new PhoneNumber("012-555-4747");
		assertFalse(pnumToTest.isTollFree());

		pnumToTest = new PhoneNumber("805-555-4747");
		assertFalse(pnumToTest.isTollFree());
	}
	
	@Test
	public void testIsTextExtension1 () // #12 ***
	{
		pnumToTest = new PhoneNumber();
		assertFalse (pnumToTest.isTextExtension("GOOF"));
	}

	@Test
	public void testIsTextExtension2 () // #13 ***
	{
		pnumToTest = new PhoneNumber("800-963-4747");
		
//		System.out.println (pnumToTest.getAreaCode());
//		System.out.println (pnumToTest.getPrefix());
//		System.out.println (pnumToTest.getSuffix());
//			
		assertTrue (pnumToTest.isTextExtension("GRIP"));	
	}

	@Test
	public void testIsTextExtension3 () // #14
	{
		pnumToTest = new PhoneNumber("651-766-0047");
		assertFalse (pnumToTest.isTextExtension("00IP"));		
	}

	@Test
	public void testIsTextExtension4 () // #15
	{
		pnumToTest = new PhoneNumber("651-766-2662");
		assertTrue (pnumToTest.isTextExtension("ANNA"));				
	}

}

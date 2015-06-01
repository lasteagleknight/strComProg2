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
	public void testDefaultConstructor() 
	{
		pnumToTest = new PhoneNumber();
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testStringConstructor2() 
	{
		pnumToTest = new PhoneNumber("651-474-4747");
		
		assertEquals(pnumToTest.getAreaCode(), "651");
		assertEquals(pnumToTest.getPrefix(), "474");
		assertEquals(pnumToTest.getSuffix(), "4747");
	}
	
	@Test
	public void testStringConstructor6()
	{
		// invalid chars => default #
		
		pnumToTest = new PhoneNumber("800474ABCD");
		
		assertEquals(pnumToTest.getAreaCode(), "800");
		assertEquals(pnumToTest.getPrefix(), "555");
		assertEquals(pnumToTest.getSuffix(), "1212");
	}
	
	@Test
	public void testGetters()
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
	public void testIsTextExtension1()
	{
		pnumToTest = new PhoneNumber();
		assertFalse (pnumToTest.isTextExtension("GOOF"));
	}

	@Test
	public void testIsTextExtension2()
	{
		pnumToTest = new PhoneNumber("800-963-4747");			
		assertTrue (pnumToTest.isTextExtension("GRIP"));	
	}
}

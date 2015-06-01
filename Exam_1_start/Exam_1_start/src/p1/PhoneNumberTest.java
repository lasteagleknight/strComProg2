package p1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PhoneNumberTest {

	PhoneNumber test = new PhoneNumber("651-434-5422");
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testPhoneNumberString() {
		PhoneNumber test1 = new PhoneNumber();
		StdOut.println("Test 1");
		StdOut.println(test1.getAreaCode());
		StdOut.println(test1.getPrefix());
		StdOut.println(test1.getSuffix());
		StdOut.println();
		
	}
	
	@Test
	public void test2PhoneNumberString() {
		PhoneNumber test2 = new PhoneNumber("651-434-5422");
		StdOut.println("test 2");
		StdOut.println(test2.getAreaCode());
		StdOut.println(test2.getPrefix());
		StdOut.println(test2.getSuffix());
		StdOut.println();
		
	}
	
	@Test
	public void test3PhoneNumberString() {
		PhoneNumber test3 = new PhoneNumber("651-4345422");
		StdOut.println("test 3");
		StdOut.println(test3.getAreaCode());
		StdOut.println(test3.getPrefix());
		StdOut.println(test3.getSuffix());
		StdOut.println();
		
	}

	@Test
	public void testIsTextExtension() {
		assertFalse(test.isTextExtension("jgaa"));
		
	}
	
	@Test
	public void test2IsTextExtension() {
		assertTrue(test.isTextExtension("JGAA"));
		
	}
	
	@Test
	public void test3IsTextExtension() {
		assertFalse(test.isTextExtension("JGAAIS"));
		
	}
	
	@Test
	public void test4IsTextExtension() {
		assertFalse(test.isTextExtension("JgAA"));
		
	}
	
	@Test
	public void test5IsTextExtension() {
		assertFalse(test.isTextExtension("TEST"));
		
	}
	
	@Test
	public void test6IsTextExtension() {
		PhoneNumber test4 = new PhoneNumber("651-434-5683");
		assertTrue(test4.isTextExtension("LOVE"));
		
	}

	@Test
	public void testIsTollFree() {
		PhoneNumber test5 = new PhoneNumber("800-434-5683");
		assertTrue(test5.isTollFree());
	}

	@Test
	public void test2IsTollFree() {
		PhoneNumber test6 = new PhoneNumber("651-434-5683");
		assertFalse(test6.isTollFree());
	}
}

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DoubleLinkedTest
{
	private DoubleLinked test1;
	
	private DoubleLinked.DNode node1;
	private DoubleLinked.DNode node2;
	private DoubleLinked.DNode node3;
	private DoubleLinked.DNode added;
	private DoubleLinked.DNode removed;
	private DoubleLinked.DNode fetched;
	
	@Before
	public void setUp () throws Exception
	{
		test1 = new DoubleLinked();
		
		node1 = new DoubleLinked.DNode();
		node1.data = "one";
		
		node2 = new DoubleLinked.DNode();
		node2.data = "two";
		
		node3 = new DoubleLinked.DNode();
		node3.data = "three";
		
		added = new DoubleLinked.DNode();
		added.data = "add";
		
	}
	
	public void initSingleNode ()
	{
		test1.insertFirst(node1);
	}
	
	public void initDoubleNode ()
	{
		test1.insertFirst(node2);
		test1.insertFirst(node1);
	}
	
	public void initTripleNode ()
	{
		test1.insertFirst(node3);
		test1.insertFirst(node2);
		test1.insertFirst(node1);
	}
	
	@Test
	public void test1empty ()
	{
		assertTrue("1.1: Testing empty DL for size==0", 0 == test1.size());
		assertTrue("1.2: Testing first, last node of empty DL", null == test1.getNth(1));
		assertTrue("1.2: Testing first, last node of empty DL", null == test1.getNth(test1.size()));
	}
	
	@Test
	public void test2getNth ()
	{
		initTripleNode();
		
		fetched = test1.getNth(1);
		
		assertTrue("2.1: Testing getNth() for size==3", fetched == node1);
		
		fetched = test1.getNth(2);
		
		assertTrue("2.2: Testing getNth() for size==3", fetched == node2);
		
		fetched = test1.getNth(3);
		
		assertTrue("2.3: Testing getNth() for size==3", fetched == node3);
		
		fetched = test1.getNth(4);
		
		assertTrue("2.4: Testing getNth() for size==3", fetched == null);
	}
	
	@Test
	public void test3emptyInsertLast ()
	{
		test1.insertLast(added);
		
		assertTrue("3.1: Testing 0 x insertLast()", 1 == test1.size());
		assertTrue("3.2: Testing 0 x insertLast()", added == test1.getNth(1));
		assertTrue("3.3: Testing 0 x insertLast()", added == test1.getNth(test1.size()));
	}
	
	@Test
	public void test4singleRemoveFirst ()
	{
		initSingleNode();
		
		removed = test1.removeFirst();
		
		assertTrue("4.1: Testing 1 x removeFirst()", 0 == test1.size());
		assertTrue("4.2: Testing 1 x removeFirst()", removed == node1);
	}
	
	@Test
	public void test5singleRemoveLast ()
	{
		initSingleNode();
		
		removed = test1.removeLast();
		
		assertTrue("5.1: Testing 1 x removeLast()", 0 == test1.size());
		assertTrue("5.2: Testing 1 x removeLast()", removed == node1);
		
	}
	
	@Test
	public void test6singleInsertFirst ()
	{
		initSingleNode();
		
		test1.insertFirst(added);
		
		assertTrue("6.1: Testing 1 x insertFirst()", 2 == test1.size());
		assertTrue("6.2: Testing 1 x insertFirst()", node1 == test1.getNth(2));
		assertTrue("6.3: Testing 1 x insertFirst()", added == test1.getNth(1));
	}
	
	@Test
	public void test7singleRemoveFirst ()
	{
		initSingleNode();
		
		removed = test1.removeFirst();
		
		assertTrue("7.1: Testing 1 x removeFirst()", 0 == test1.size());
		assertTrue("7.2: Testing 1 x removeFirst()", removed == node1);
		assertTrue("7.3: Testing 1 x removeFirst()", null == test1.getNth(1));
	}
	
	@Test
	public void test8doubleInsertLast ()
	{
		initDoubleNode();
		
		test1.insertLast(added);
		
		assertTrue("8.1: Testing 2 x insertLast()", 3 == test1.size());
		assertTrue("8.2: Testing 2 x insertLast()", added == test1.getNth(3));
		assertTrue("8.3: Testing 2 x insertLast()", node2 == test1.getNth(2));
	}
	
	@Test
	public void test9doubleRemoveLast ()
	{
		initDoubleNode();
		
		removed = test1.removeLast();
		
		assertTrue("9.1: Testing 2 x removeLast()", 1 == test1.size());
		assertTrue("9.2: Testing 2 x removeLast()", removed == node2);
		assertTrue("9.3: Testing 2 x removeLast()", node1 == test1.getNth(1));
	}
	
	@Test
	public void test10insertBeforeHead ()
	{
		initTripleNode();
		
		test1.insertBefore(node1, added);
		
		assertTrue("10.1: Testing 3 x insertBeforeHead()", 4 == test1.size());
		assertTrue("10.2: Testing 3 x insertBeforeHead()", added.next == node1);
		assertTrue("10.3: Testing 3 x insertBeforeHead()", node1.prev == added);
		assertTrue("10.4: Testing 3 x insertBeforeHead()", added == test1.getNth(1));
	}
	
	@Test
	public void test11insertBeforeTail ()
	{
		initTripleNode();
		
		test1.insertBefore(node3, added);
		
		assertTrue("11.1: Testing 3 x insertBeforeHead()", 4 == test1.size());
		assertTrue("11.2: Testing 3 x insertBeforeHead()", added.next == node3);
		assertTrue("11.3: Testing 3 x insertBeforeHead()", node3.prev == added);
		assertTrue("11.4: Testing 3 x insertBeforeHead()", added == test1.getNth(3));		
	}
	
	@Test
	public void test12insertBeforeMiddle ()
	{
		initTripleNode();
		
		test1.insertBefore(node2, added);
		
		assertTrue("12.1: Testing 3 x insertBeforeHead()", 4 == test1.size());
		assertTrue("12.2: Testing 3 x insertBeforeHead()", added.next == node2);
		assertTrue("12.3: Testing 3 x insertBeforeHead()", node2.prev == added);
		assertTrue("12.4: Testing 3 x insertBeforeHead()", added == test1.getNth(2));	
	}
	
	@Test
	public void test13removeNth1 ()
	{
		initTripleNode();
		
		removed = test1.removeNth(1);
		
		assertTrue("13.1: Testing 3 x removeNth()", 2 == test1.size());
		assertTrue("13.2: Testing 3 x removeNth()", removed == node1);
		assertTrue("13.3: Testing 3 x removeNth()", node2 == test1.getNth(1));	
		assertTrue("13.4: Testing 3 x removeNth()", node3 == test1.getNth(2));	
	}
	
	@Test
	public void test14removeNth2 ()
	{
		initTripleNode();
		
		removed = test1.removeNth(2);
		
		assertTrue("14.1: Testing 3 x removeNth()", 2 == test1.size());
		assertTrue("14.2: Testing 3 x removeNth()", removed == node2);
		assertTrue("14.4: Testing 3 x removeNth()", node1 == test1.getNth(1));	
		assertTrue("14.4: Testing 3 x removeNth()", node3 == test1.getNth(2));	
	}
	
	@Test
	public void test15removeNth3 ()
	{
		initTripleNode();
		
		removed = test1.removeNth(3);
		
		assertTrue("15.1: Testing 3 x removeNth()", 2 == test1.size());
		assertTrue("15.2: Testing 3 x removeNth()", removed == node3);
		assertTrue("15.3: Testing 3 x removeNth()", node1 == test1.getNth(1));
		assertTrue("15.4: Testing 3 x removeNth()", node2 == test1.getNth(2));	
	}
	
	@Test
	public void test16()
	{
		//fail("Not yet implemented");
	}
	
	@Test
	public void test17 ()
	{
		//fail("Not yet implemented");
	}
	
	@Test
	public void test18 ()
	{
		//fail("Not yet implemented");
	}
	
	@Test
	public void test19 ()
	{
		//fail("Not yet implemented");
	}
	
	@Test
	public void test20 ()
	{
		//fail("Not yet implemented");
	}
	
}

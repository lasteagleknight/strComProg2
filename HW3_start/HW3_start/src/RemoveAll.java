// Complete the method remove(String,Node<String>) for HW 3 Problem 5

public class RemoveAll
{
	
	public static void main (String[] args)
	{
		Node<String> first = new Node<String>("one");
		Node<String> second = new Node<String>("two");
		Node<String> third = new Node<String>("three");
		Node<String> fourth = new Node<String>("two");
		Node<String> fifth = new Node<String>("two");
		
		first.next = second;
		second.next = third;
		third.next = fourth;
		fourth.next = fifth;
		fifth.next = null;
		
		StdOut.println("Before:");
		printList(first);
		
		first = remove("two", first);
		
		StdOut.println("\nAfter:");
		printList(first);
	}
	
	// helper linked list class
	private static class Node<Item>
	{
		private Item item;
		private Node<Item> next;
		
		public Node (Item it)
		{
			item = it;
		}
	}
	
	public static void printList (Node<String> startNode)
	{
		Node<String> node = startNode;
		
		while (node != null)
		{
			StdOut.println(node.item);
			node = node.next;
		}
	}
	
	public static Node<String> remove (String key, Node<String> head)
	{
		if(head == null)
			return null;
		
		if(head.item.contentEquals(key)){
			head =  remove(key, head.next);	
		}
		
		
		else if(head.next.item.contentEquals(key)){
			if(head.next.next != null){
				head.next = remove(key, head.next.next);
			}
			else
				head.next = null;
		}
				
		return head;
		
	}
	
}

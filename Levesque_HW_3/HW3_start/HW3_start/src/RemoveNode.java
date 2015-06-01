// HW 3, Problem 4 starting code: complete removeAfter(Node<String> head)
//

public class RemoveNode
{
	
	public static void main (String[] args)
	{
		Node<String> first = new Node<String>("one");
		Node<String> second = new Node<String>("two");
		Node<String> third = new Node<String>("three");
		
		first.next = second;
		second.next = third;
		third.next = null;
		
		StdOut.println("Before:");
		printList(first);
		
		removeAfter(first);
		
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
	
	public static void removeAfter (Node<String> node)
	{
		if(node.next != null && node.next.next != null){
			
		node.next = node.next.next;
		}
		
	}
}

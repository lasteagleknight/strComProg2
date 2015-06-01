
/**
 * HW 5, Problem 5
 * 
 * @author eric
 *
 */
public class LinkedListSort
{
	static int count = 0;
	static int lo = 0;
	static int hi = 0;
	static int mid = 0;
	
	public static class Node
	{
		public String data;
		public Node next;
		
		public Node () { }
		public Node (String d, Node n)
		{
			data = d;
			next = n;
		}
	}
	
	public static Node sort (Node head, Node tail)
	{
		count = Count(head);
		mid = (count/2);
		
		return sort(head, 0, count);
	}
	
	
	
	public static Node sort(Node head, int low, int high){
		
		int mid = Count(head);
		if(mid%2 == 0) mid = mid/2;
		else mid = (mid/2)+1;
		
		if(high <= 1){ 
			head.next = null;
			return head;
		}
		
		
		Node middle = mid(head, mid);
		Node sort1 = sort(head, 0, mid);
		Node sort2 =sort(middle, 0, high-mid);
		
		Node m = merge(sort1,sort2,mid, low, high);
		
		return m;
	}
	
	public static Node merge(Node lo, Node middle, int mid, int low, int high){
		
		Node forward = lo, back = middle, start, marker;
		int i = low, j = mid +1;
		
		if(less(forward.data,back.data)){
			start = forward;
			forward = forward.next;
		}
		else {
			start = back;
			back = back.next;	
		}
		
		marker = start;
		
		for(int k = 1; k <= high; k++){
			if(Count(lo) > mid){
				marker = marker.next = back;
				if(back != null) back = back.next;
				if(marker != null)marker.next = null;
			}
			else if(Count(back) > high){
				marker = marker.next = forward;
				if(forward != null)forward = forward.next;
				if(marker != null)marker.next = null;
				
			}
			else if(back == null && forward != null){
				marker = marker.next = forward;
				if(forward != null)forward = forward.next;
				if(marker != null)marker.next = null;
				
			}
			else if(forward != null){
				if(less(forward.data, back.data)){
					marker = marker.next = forward;
					if(forward != null)forward = forward.next;
					if(marker != null)marker.next = null;
					
				}
				else{
						marker = marker.next = back;
						if(back != null)back = back.next;
						if(marker != null)marker.next = null;
				}
			}
			else{
				if(marker != null)
				marker.next = back;
				if(marker != null)
					marker = marker.next;
				if(back != null)
					back = back.next;
				if(marker != null)
					marker.next = null;
			}
			
		}//end of for merge
		
		return start;
	}
	
	public static int Count(Node head){

		Node count = head;
		if(count == null) return 0;
		int counter = 1;
		while(count.next != null){
			count = count.next;
			counter++;
		}
		return counter;
	}
	
	public static Node end(Node head, int end){ // kills the end of the node
		
		Node tail = head;
		for(int i = 0; i < end;i++){
			if(tail.next != null)
				tail = tail.next;
		}
		tail.next = null;
		
		return head;
	}
	
	public static Node mid(Node head, int mid){ // call for middle node
		
		Node middle = head;
		Node cut = head;
		for(int i = 0; i < mid;i++){
			if(middle.next != null)
				middle = middle.next;
			if (i > 0){
				cut = cut.next;
			}
		}
		cut.next = null;
		return middle;
	}
	
public static Node cutHead(Node head, int mid){ // call for middle node
		
		Node middle = head;
		Node cut = head;
		for(int i = 0; i < mid;i++){
			if(middle.next != null)
				middle = middle.next;
			if (i > 0){
				cut = cut.next;
			}
		}
		cut.next = null;
		return head;
	}
	
	private static boolean less(Comparable v, Comparable w) {
	        return (v.compareTo(w) < 0);
	    }
	
	private static void list(Node head){
		do{
			StdOut.print(head.data);
			head = head.next;
		}while(head != null);
		StdOut.println();
	}
	
	public static void main (String[] args)
	{
		Node a =  new Node("a",null),
						d =  new Node("d",a),
						z =  new Node("z",d),
						w =  new Node("w",z),
						k =  new Node("k",w),
						f = new Node("f",k),
						b =  new Node("b",f),
						c =  new Node("c",b),
						e =  new Node("e",c);
						
		Node next = e;
		
		list(next);
		list(sort(e,a));
		
	}
	
	
	
}

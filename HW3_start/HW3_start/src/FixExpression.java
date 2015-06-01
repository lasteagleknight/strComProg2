// HW 3, Problem 3: print out fully-parenthesized expression of input expression

public class FixExpression
{
	public static void main (String[] args)
	{
		Stack<String> ops = new Stack<String>();
		Stack<String> vals = new Stack<String>();
		
		String[] inStrings = new String[20];
		int next = 0;
		
		while (!StdIn.isEmpty())
		{
			String s = StdIn.readString();
			inStrings[next++] = s;
			// StdOut.println (s);
			
			if (s.equals("("))
				;
			else if (s.equals("+"))
				ops.push(s);
			else if (s.equals("-"))
				ops.push(s);
			else if (s.equals("*"))
				ops.push(s);
			else if (s.equals("/"))
				ops.push(s);
			else if (s.equals("sqrt"))
				ops.push(s);
			else if (s.equals(")"))
			{
				String op = ops.pop();
				String v = vals.pop();
				if (op.equals("+"))
					v = " ( " + vals.pop() +" + " +v +" ) ";
				else if (op.equals("-"))
					v = " ( " + vals.pop() +" - " +v +" ) ";
				else if (op.equals("*"))
					v = " ( " + vals.pop() +" * " +v +" ) ";
				else if (op.equals("/"))
					v = " ( " + vals.pop() +" / " +v +" ) ";
				
				vals.push(v);
			}
			else
				vals.push(s);
		}
		
		
		// for (String s : inStrings)
		// {
		// if (s != null)
		// StdOut.print(s);
		// }
		
		StdOut.println(vals.pop());
		
	}
	
}

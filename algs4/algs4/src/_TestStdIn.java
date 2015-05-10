public class _TestStdIn {

	public static void main(String[] args) {

		StdOut.println("starting.");

		String[] ins = StdIn.readAllStrings();
	
		StdOut.println(ins.length);

		for (String s : ins)
			StdOut.println(s);
	}
}

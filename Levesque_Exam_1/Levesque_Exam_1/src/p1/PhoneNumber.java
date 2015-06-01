package p1;


/**
 * Each PhoneNumber instance represents a single U.S. telephone number of the
 * form:
 * 
 * AAA-PPP-EEEE
 * 
 * where AAA is the three-digit area code, 
 * PPPP is the three-digit prefix ("exchange"), and
 * SSS is the four-digit suffix ("extension").
 * 
 * Exam 1, Problem 1 CSci 2002.91 Spring 2014
 * 
 * @author eric
 * 
 */
public class PhoneNumber
{
	static String areaCode = "800";
	static String prefix = "555";
	static String suffix = "1212";
	
	
	public PhoneNumber (String arg)
	{
		if(arg.length() == 12){
			areaCode = arg.substring(0, 3);
			prefix = arg.substring(4, 7);
			suffix = arg.substring(8, 12);
		}
		try{
			int i = (Integer.parseInt(areaCode));
			i = (Integer.parseInt(prefix));
			i = (Integer.parseInt(suffix));
		}
		catch(NumberFormatException nfe){
			areaCode = "800";
			prefix = "555";
			suffix = "1212";
		}
	}
	
	public PhoneNumber ()
	{
		areaCode = "800";
		prefix = "555";
		suffix = "1212";
	}
	
	public String getAreaCode ()
	{
		return areaCode;
	}
	
	public String getPrefix ()
	{
		return prefix;
	}
	
	public String getSuffix ()
	{
		return suffix;
	}
	
	public boolean isTextExtension (String text)
	{
		boolean cheak = false;
		String textUpper = text.toUpperCase();
		if(text.length() == 4){
			for(int i = 0; i < text.length();i++){
				if(text.charAt(i) == textUpper.charAt(i)){
					if(num(text.charAt(i)) == suffix.charAt(i)){
						cheak = true;
					}
				}
				else{
					cheak = false;
					break;
				}
			}
		}
		return cheak;
	}
	
	@SuppressWarnings("null")
	public char num(char a){
		
		switch(a){
		case 'A': case 'B': case 'C':
			a = '2';
			break;
		case 'D': case 'E': case 'F':
			a = '3';
			break;
		case 'G': case 'H': case 'I':
			a = '4';
			break;
		case 'J': case 'K': case 'L':
			a = '5';
			break;
		case 'M': case 'N': case 'O':
			a = '6';
			break;
		case 'P': case 'Q': case 'R': case 'S':
			a = '7';
			break;
		case 'T': case 'U': case 'V':
			a = '8';
			break;
		case 'W': case 'X': case 'Y': case 'Z':
			a = '9';
			break;
		default:
			a = (Character) null;
			
		}
			
		return a;
		
	}
	
	public boolean isTollFree ()
	{
		String[] tollfree = { "800","866","877",
				"880","881","882","888"};
		for(int i = 0; i < tollfree.length;i++){
			if(areaCode.compareTo(tollfree[i]) == 0){
				return true;
			}
		}
		
		return false;
	}
	
	public static void main (String[] args)
	{
		
	}
	
}

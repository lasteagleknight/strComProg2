/**
 * Problem 4, HW 2:
 * 
 * Starting (Date) code for Problem 1.2.11 from book:
 * 	As discussed in HW Help video and HW 2 handout, modify the code
 *  below to throw exception when bad format ints are used in constructors.
 * 
 * @author eric
 *
 */

public class Problem4 implements Comparable<Problem4>
{
	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private final int month;   // month (between 1 and 12)
	private final int day;     // day (between 1 and DAYS[month]
	private final int year;    // year
	
	/**
	 * Initializes a new date from the month, day, and year.
	 * 
	 * @param month
	 *            the month (between 1 and 12)
	 * @param day
	 *            the day (between 1 and 28-31, depending on the month)
	 * @param year
	 *            the year
	 * @throws IllegalArgumentException
	 *             if the date is invalid
	 */
	public Problem4 (int month, int day, int year)
	{
		if (!isValid(month, day, year))
			throw new IllegalArgumentException("Invalid date");
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	/**
	 * Initializes new date specified as a string in form MM/DD/YYYY.
	 * 
	 * @param date
	 *            the string representation of the date
	 * @throws IllegalArgumentException
	 *             if the date is invalid
	 */
	public Problem4 (String date)
	{
		String[] fields = date.split("/");
		if (fields.length != 3)
		{
			
			throw new IllegalArgumentException("Invalid date");
		}
		try
		{
		month = Integer.parseInt(fields[0]);
		}
		catch (NumberFormatException e)
		{
			StdOut.printf ("'%s' is not a valid int.  Throwing IllegalArgumentException...\n\n", fields[0]);
			throw new IllegalArgumentException("bad int format");
		}
		try
		{
		day = Integer.parseInt(fields[1]);
		}
		catch (NumberFormatException e)
		{
			StdOut.printf ("'%s' is not a valid int.  Throwing IllegalArgumentException...\n\n", fields[1]);
			throw new IllegalArgumentException("bad int format");
		}
		try
		{
		year = Integer.parseInt(fields[2]);
		}
		catch (NumberFormatException e)
		{
			StdOut.printf ("'%s' is not a valid int.  Throwing IllegalArgumentException...\n\n", fields[2]);
			throw new IllegalArgumentException("bad int format");
		}
		if (!isValid(month, day, year))
			throw new IllegalArgumentException("Invalid date");
	}
	
	/**
	 * Return the month.
	 * 
	 * @return the month (an integer between 1 and 12)
	 */
	public int month ()
	{
		return month;
	}
	
	/**
	 * Return the day.
	 * 
	 * @return the day (an integer between 1 and 31)
	 */
	public int day ()
	{
		return day;
	}
	
	/**
	 * Return the year.
	 * 
	 * @return the year
	 */
	public int year ()
	{
		return year;
	}
	
	// is the given date valid?
	private static boolean isValid (int m, int d, int y)
	{
		if (m < 1 || m > 12)
			return false;
		if (d < 1 || d > DAYS[m])
			return false;
		if (m == 2 && d == 29 && !isLeapYear(y))
			return false;
		return true;
	}
	
	/**
	 * Is year y a leap year?
	 * 
	 * @return true if y is a leap year; false otherwise
	 */
	private static boolean isLeapYear (int y)
	{
		if (y % 400 == 0)
			return true;
		if (y % 100 == 0)
			return false;
		return y % 4 == 0;
	}
	
	/**
	 * Returns the next date in the calendar.
	 * 
	 * @return a date that represents the next day after this day
	 */
	public Problem4 next ()
	{
		if (isValid(month, day + 1, year))
			return new Problem4(month, day + 1, year);
		else if (isValid(month + 1, 1, year))
			return new Problem4(month + 1, 1, year);
		else
			return new Problem4(1, 1, year + 1);
	}
	
	/**
	 * Is this date after b?
	 * 
	 * @return true if this date is after date b; false otherwise
	 */
	public boolean isAfter (Problem4 b)
	{
		return compareTo(b) > 0;
	}
	
	/**
	 * Is this date before b?
	 * 
	 * @return true if this date is before date b; false otherwise
	 */
	public boolean isBefore (Problem4 b)
	{
		return compareTo(b) < 0;
	}
	
	/**
	 * Compare this date to that date.
	 * 
	 * @return { a negative integer, zero, or a positive integer }, depending on
	 *         whether this date is { before, equal to, after } that date
	 */
	public int compareTo (Problem4 that)
	{
		if (this.year < that.year)
			return -1;
		if (this.year > that.year)
			return +1;
		if (this.month < that.month)
			return -1;
		if (this.month > that.month)
			return +1;
		if (this.day < that.day)
			return -1;
		if (this.day > that.day)
			return +1;
		return 0;
	}
	
	/**
	 * Return a string representation of this date.
	 * 
	 * @return the string representation in the foramt MM/DD/YYYY
	 */
	public String toString ()
	{
		return month + "/" + day + "/" + year;
	}
	
	/**
	 * Is this date equal to x?
	 * 
	 * @return true if this date equals x; false otherwise
	 */
	public boolean equals (Object x)
	{
		if (x == this)
			return true;
		if (x == null)
			return false;
		if (x.getClass() != this.getClass())
			return false;
		Problem4 that = (Problem4)x;
		return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
	}
	
	/**
	 * Return a hash code.
	 * 
	 * @return a hash code for this date
	 */
	public int hashCode ()
	{
		int hash = 17;
		hash = 31 * hash + month;
		hash = 31 * hash + day;
		hash = 31 * hash + year;
		return hash;
	}
	
	/**
	 * Unit tests the date data type.
	 */
	public static void main (String[] args)
	{
		Problem4 today = new Problem4("2/25/2014");
		StdOut.println(today);
		for (int i = 0; i < 10; i++)
		{
			today = today.next();
			StdOut.println(today);
		}
		
		StdOut.println(today.isAfter(today.next()));
		StdOut.println(today.isAfter(today));
		StdOut.println(today.next().isAfter(today));
		
		Date birthday = new Date(10, 16, 1971);
		StdOut.println(birthday);
		for (int i = 0; i < 10; i++)
		{
			birthday = birthday.next();
			StdOut.println(birthday);
		}
	}
	
}

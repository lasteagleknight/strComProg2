/**
 * Part of HW 6, Problem 1
 * 
 * Represents a single McDonalds store
 * 
 */

public class McDonalds implements Comparable<McDonalds>
{
	public double latitude;
	public double longitude;
	public String name;
	public String addressInfo;
	
	public int sequence;
	
	public McDonalds(double lng, double lat, String n, String a, int seq)
	{
		this.latitude = lng;
		this.longitude = lat;
		this.name = n;
		this.addressInfo = a;
		this.sequence = seq;
	}
	
	public int compareTo(McDonalds other)
	{
		if (longitude < other.longitude)
			return -1;
		else if (longitude > other.longitude)
			return +1;
		else
			return 0;
	}
	
	public String toString()
	{
		return (latitude + "," + longitude + ") - " + 
				name + ", " + addressInfo + " @ " + sequence);
	}
}


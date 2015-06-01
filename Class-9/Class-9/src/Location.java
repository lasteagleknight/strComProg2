
public class Location implements Comparable<Location>
{
	public double latitude;
	public double longitude;
	public int sequence;
	
	public Location(double latitude, double longitude, int seq)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.sequence = seq;
	}
	
	public int compareTo(Location other)
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
		return (latitude + "," + longitude + " @ " + sequence);
	}
}

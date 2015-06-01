/**
 * Interface for alternate priority queue
 * 	implementations from book
 * 
 * @author eric
 *
 * @param <Key>
 */

import java.util.Iterator;

public interface PQ<Key> extends Iterable<Key>
{
	public boolean isEmpty();
	public int size();
	public Key max();
	public void insert(Key x);
	public Key delMax();
	public Iterator<Key> iterator();
}

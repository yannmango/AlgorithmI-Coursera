import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int n = 0;
    public RandomizedQueue() {                 // construct an empty randomized queue
        s = (Item[]) new Object[1];
    }
    public boolean isEmpty() {                 // is the queue empty?
        return n == 0;
    }
    public int size() {                       // return the number of items on the queue
        return n;
    }
    public void enqueue(Item item) {          // add the item
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (n == s.length) resize(2*s.length);
        s[n++] = item;
    }
    public Item dequeue() {                   // remove and return a random item
	    if (n == 0) throw new java.util.NoSuchElementException();
	    int rand = StdRandom.uniform(0, n);
	    Item temp = s[rand];
	    s[rand] = s[n-1];
	    s[n-1] = null;
	    n--;
	    return temp;
	}
    public Item sample() {                    // return (but do not remove) a random item
	    if (n == 0) throw new java.util.NoSuchElementException();
	    int rand = StdRandom.uniform(0, n);
	    return s[rand];
	}
    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
	    return new RandQueueIterator();
    }
    private class RandQueueIterator implements Iterator<Item> {
	    private int index;
	    private final int[] arr;
	    public RandQueueIterator() {
		    index = 0;
		    arr = new int[n];
		    for (int i = 0; i < n; i++) {
			    arr[i] = i;
		    }
		    StdRandom.shuffle(arr);
	    }
	    public boolean hasNext() {
		    return index < n;
	    }
	    public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			Item item = s[arr[index]];
			index++;
			return item;
		}
    }
    private void resize(int capacity) {
	    Item[] copy = (Item[]) new Object[capacity];
	    for (int i = 0; i < n; i++)
		    copy[i] = s[i];
	    s = copy;
    }
    public static void main(String[] args) {  // unit testing (optional)
//	    RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
//	    rq.enqueue(3);
//	    rq.enqueue(4);
//	    rq.enqueue(5);
//	    Iterator<Integer> it = rq.iterator();
//	    while (it.hasNext()) {
//		    StdOut.println(it.next());
//	    }
    }
}

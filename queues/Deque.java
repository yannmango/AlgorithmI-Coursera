import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
	public Deque() {                           // construct an empty deque
		first = null;
		last = null;
		size = 0;
	}
	public boolean isEmpty() {                 // is the deque empty?
		return first == null;
	}
	public int size() {                       // return the number of items on the deque
		return size;
	}
	public void addFirst(Item item) {         // add the item to the front
		if (item == null) throw new java.lang.IllegalArgumentException();
		Node node = new Node();
		node.item = item;
		if (first == null) {
			first = node;
			last = first;
		}
		else {
			node.next = first;
			first.prev = node;
			first = node;
		}
		size++;
	}
	public void addLast(Item item) {          // add the item to the end
		if (item == null) throw new java.lang.IllegalArgumentException();
		Node node = new Node();
		node.item = item;
		if (last == null) {
			last = node;
			first = last;
		}
		else {
			last.next = node;
			node.prev = last;
			last = node;
		}
		size++;
	}
	public Item removeFirst() {               // remove and return the item from the front
		if (first == null) throw new java.util.NoSuchElementException();
		Item item = first.item;
		if (first == last) {
			first = null;
			last = null;
		}
		else {
			first = first.next;
			first.prev = null;
		}
		size--;
		return item;
	}
	public Item removeLast() {                // remove and return the item from the end
		if (last == null) throw new java.util.NoSuchElementException();
		Item item = last.item;
		if (first == last) {
			last = null;
			first = null;
		}
		else {
			last = last.prev;
			last.next = null;
		}
		size--;
		return item;
	}
	public Iterator<Item> iterator() {       // return an iterator over items in order from front to end
		return new DequeIterator();
	}
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {   // unit testing (optional)
		Deque<Integer> deque = new Deque<Integer>();
	    deque.addFirst(3);
	    deque.addLast(2);
	    deque.removeFirst();
	    deque.removeLast();
	    Iterator<Integer> it = deque.iterator();
	    while (it.hasNext()) {
	    	int i = it.next();
	    	StdOut.println(i);	 
	    }
	    
	}

}

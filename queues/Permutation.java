//import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Permutation {
    public static void main(String[] args) {
//		int k = StdIn.readInt();
    	int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> q = new RandomizedQueue<String>();
//	    In in = new In(StdIn.readString());
//		String[] str = in.readAllStrings();
//		StdRandom.shuffle(str);
//		for (int i = 0; i < k; i++) {
//			q.enqueue(str[i]);
//		}
//		Iterator<String> it = q.iterator();
//		while (it.hasNext()) {
//			StdOut.println(it.next());
//		}
//		for(int i = 0; i< str.length; i++) {
//			q.enqueue(str[i]);
//		}
		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}
		for (int i = 0; i < k; i++) {
			StdOut.println(q.dequeue());
		}
	}
}

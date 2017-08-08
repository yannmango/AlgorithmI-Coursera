import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private boolean solvable = false;
	private final List<Board> res = new LinkedList<Board>();
	private class Node {
		int moves;
		Board present;
		Node previous;
		public Node(int moves, Board present, Node previous) {
			super();
			this.moves = moves;
			this.present = present;
			this.previous = previous;
		}
		public int getPriority() {
			return this.present.manhattan() + this.moves;
		}
		public Node getPreviousNode() {
			return this.previous;
		}
		public Board getPresentBoard() {
			return this.present;
		}
		public int getMoves() {
			return this.moves;
		}
	}
    public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
    	if (initial == null) throw new java.lang.IllegalArgumentException();
    	MinPQ<Node> pq; 
    	pq = new MinPQ<Node>(new priorityComparable());
    	pq.insert(new Node(0, initial, null));
    	pq.insert(new Node(0, initial.twin(),null));
//    	StdOut.println(pq.size());
    	while(!pq.min().getPresentBoard().isGoal()) {
    		Node out = pq.delMin();
    		Iterable<Board> list = out.getPresentBoard().neighbors();
    		for (Board b : list) {
    			if (!checkPreviousAppeared(b, out))
    				pq.insert(new Node(out.getMoves() + 1, b, out));
//    			if(!b.equals(out.getPreviousNode().getPresentBoard()))
//    				pq.insert(new Node(out.getMoves() + 1, b, out));
//    			
    		}
//    		StdOut.println(pq.size());
    	}
    	Node solutionNode = pq.delMin();
    	Node copySolution = solutionNode;
    	res.add(copySolution.getPresentBoard());
    	while ((copySolution = copySolution.getPreviousNode()) != null) {
    		res.add(0,copySolution.getPresentBoard());
    	}
    	solvable = res.get(0).equals(initial);
    }
    public boolean isSolvable() {           // is the initial board solvable?
    	return solvable;
    }
    
    public int moves() {                    // min number of moves to solve initial board; -1 if unsolvable
    	if(solvable) {
    		return res.size() - 1;
    	} else {
    		return -1;
    	}
    }
    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
    	if (solvable) {
    		return res;
    	} else {
    		return null;
    	}
    }
    private class priorityComparable implements Comparator<Node> {
    	public int compare(Node a, Node b) {
    		return a.getPriority() - b.getPriority();
    	}
    }
    private boolean checkPreviousAppeared(Board board, Node node) {
    	Node copy = node;
    	while ((copy = copy.getPreviousNode()) != null) {
    		if (copy.getPresentBoard().equals(board)) {
    			return true;
    		}
    	}
    	return false;
    }
    public static void main(String[] args) {// solve a slider puzzle (given below)
//    	int[][] blocks = {{0,1,3},{4,2,5},{7,8,6}};
//    	Board initial = new Board(blocks);
//    	Solver solver = new Solver(initial);
//    	StdOut.println(solver.isSolvable());
//    	StdOut.println(solver.moves());
    }
}
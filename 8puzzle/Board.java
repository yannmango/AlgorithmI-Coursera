import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
	private final int[][] boards;
//	private final List<Board> list ;
	private final int len;
    public Board(int[][] blocks) {          // construct a board from an n-by-n array of blocks
    	if(blocks == null) throw new java.lang.IllegalArgumentException(); //blocks cannot be null
    	len = blocks.length;
//    	list = new LinkedList<>();
    	boards = new int[len][len];   //copy blocks array into boards
    	for (int i = 0; i < len; i++) {
    		for (int j = 0 ; j < len; j++) {
    			boards[i][j] = blocks[i][j];
    		}
    	}
    }
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension() {                // board dimension n
    	return len;
    }
    public int hamming() {                  // number of blocks out of place
    	int hDis = 0;
    	/* scan through the array, 
    	 * if element does not equals to corresponding goal array,dis+1
    	 * else continue to exam next element
    	 * */
    	for (int i = 0; i < len; i++) {  
    		for (int j = 0 ; j < len; j++) {
    			if (i == len - 1 && j == len -1) continue;
    			if (boards[i][j] == i * len + j + 1) continue;
    			hDis += 1;
    		}
    	}
    	return hDis;
    }
    public int manhattan() {                // sum of Manhattan distances between blocks and goal
    	int mDis = 0;
    	/* scan through the array, 
    	 * if element does not equals to corresponding goal array,find the index[x,y] of present
    	 * goal element exists in the array, dis += |x-i| +|y - j|
    	 * else continue to exam next element
    	 * */
    	for (int i = 0; i < len; i++) {  
    		for (int j = 0 ; j < len; j++) {
    			if (i == len - 1 && j == len -1) continue;
    			if (boards[i][j] == i * len + j + 1) continue;
    			int[] res = findIndex(i * len + j + 1, boards);
    			mDis += Math.abs(i - res[0]) + Math.abs(j - res[1]);
    		}
    	}
    	return mDis;
    }
    public boolean isGoal() {               // is this board the goal board?
    	/* if every element in board equals to corresponding element in goal return true
    	 * else return false
    	 * */
    	for (int i = 0; i < len; i++) {
    		for (int j = 0 ; j < len; j++) {
    			if (i == len - 1 && j == len -1) {
    				if(boards[i][j] == 0) continue;
    				else return false;
    			}
    			if (boards[i][j] != i * len + j + 1) return false;
    		}
    	}
    	return true;
    }
    public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
    	int len = boards.length;
    	int[] arr1;
    	int[] arr2;
    	int[][] copy = new int[len][len];   //copy blocks array into boards
    	for (int i = 0; i < len; i++) {
    		for (int j = 0 ; j < len; j++) {
    			copy[i][j] = boards[i][j];
    		}
    	}
    	do {
    		arr1 = generateRandomIndex();
    		arr2 = generateRandomIndex();
    	} while(arr1[0] == arr2[0] && arr1[1] == arr2[1]);
    	int temp = copy[arr1[0]][arr1[1]];
    	copy[arr1[0]][arr1[1]] = copy[arr2[0]][arr2[1]];
    	copy[arr2[0]][arr2[1]] = temp;
    	
    	return new Board(copy);
    }
    
    public boolean equals(Object y) {       // does this board equal y?
    	if (y == null || getClass() != y.getClass()) return false;
    	if(this == y) return true;
    	Board copyY = (Board)y;
    	int[][] yboards = copyY.getboards();
    	if (this.boards.length != copyY.boards.length) return false;
    	for (int i = 0; i < len; i++) {
    		if (this.boards[i].length != copyY.boards.length) return false;
    		for (int j = 0 ; j < len; j++) {
    			if (this.boards[i][j] != yboards[i][j]) return false;
    		}
    	}
    	return true;
    }
    public Iterable<Board> neighbors() {    // all neighboring boards
    	return findAllneighbors(boards);
    }
    public String toString() {              // string representation of this board (in the output format specified below)
    	StringBuilder sb = new StringBuilder();
    	sb.append(len + "\n");
    	for (int i = 0; i < len; i++) {
    		for (int j = 0; j < len; j++) {
    			sb.append(String.format("%2d ", boards[i][j]));
    		}
    		sb.append("\n");
    	}
    	return sb.toString();
    }
    
    private int[] findIndex(int target, int[][] array) {
    	int[] res = new int[2];
    	for(int i = 0; i < array.length; i++) {
    		for (int j = 0; j < array[0].length; j++) {
    			if(array[i][j] == target) {
    				res[0] = i;
    				res[1] = j;
    			}
    		}
    	}
    	return res;
    }
    private int[] generateRandomIndex() {
    	int[] res = new int[2];
    	int x;
    	int y;
    	do {
    		x = StdRandom.uniform(0, len);
    		y = StdRandom.uniform(0, len);
    	}while(boards[x][y] == 0);
    	res[0] = x;
    	res[1] = y;
    	return res;
    }
    private int[][] getboards() {
    	return boards;
    }
    private List<Board> findAllneighbors(int[][] array) {
    	List<Board> list = new LinkedList<Board>();
    	int[] indexOfZero = findIndex(0, array);
    	int x = indexOfZero[0];
    	int y = indexOfZero[1];
    	if (x > 0) {
    		list.add(generateBoards(x, y, x - 1, y));
    	}
    	if (x < len - 1) {
    		list.add(generateBoards(x, y, x + 1, y));
    	}
    	if (y > 0) {
    		list.add(generateBoards(x, y, x, y - 1));
    	}
    	if (y < len - 1) {
    		list.add(generateBoards(x, y, x, y + 1));
    	}
    	return list;
    	
    }
    private Board generateBoards(int x, int y, int m, int n) {
    	int[][] copy = new int[len][len];
    	for (int i = 0; i < len; i++) {
    		for (int j = 0; j < len; j++){
    			copy[i][j] = boards[i][j];
    		}
    	}
    	exchangeItem(copy,x,y,m,n);
    	return new Board(copy);
    }
    private void exchangeItem(int[][] array, int x, int y, int m, int n) {
    	int tmp = array[x][y];
    	array[x][y] = array[m][n];
    	array[m][n] = tmp;
    }
    public static void main(String[] args) {// unit tests (not graded)
//    	In in = new In(args[0]);
//	    int n = in.readInt();
//	    int[][] blocks = {{1,2,3},{4,5,16},{7,8,0}};
//	    for (int i = 0; i < n; i++)
//	        for (int j = 0; j < n; j++)
//	            blocks[i][j] = in.readInt();
//	    int[][] copys = {{1,2,3},{4,5,6},{7,8,9}};
//	    Board initial = new Board(blocks);
//	    Board compare = new Board(copys);
//	    StdOut.println(initial.dimension());
//	    StdOut.println(initial.hamming());
//	    StdOut.println(initial.manhattan());
//	    StdOut.println(initial.isGoal());
//	    StdOut.println(""+initial.toString());
//	    StdOut.println(initial.equals(compare));
//	    for(int i=0;i<blocks.length*blocks.length;i++)
//	    	StdOut.println(initial[i]);
//	    Iterable<Board> list= initial.neighbors();
//	    StdOut.println(((List<Board>) list).size());
//	    Iterable<Board> list2= initial.neighbors();
//	    StdOut.println(((List<Board>) list2).size());
//	    for (Board b : list) {
//	    	StdOut.println(b.toString());
//	    }
    }
}

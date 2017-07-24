import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private static final int TOP = 0;
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF checkSites;
    private int size;
    private int bottom;
    private boolean[][] state;
    private int counter = 0;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException("input");
        else {
            size = n;
            sites = new WeightedQuickUnionUF(size*size+2); 
            checkSites = new WeightedQuickUnionUF(size*size+1);
            bottom = size*size+1;
            state = new boolean[size][size];
        }
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            counter++;
            state[row-1][col-1] = true;
            int p = xyTo1D(row, col);
            int q;
            if (row == 1) {
                sites.union(p, TOP);
                checkSites.union(p, TOP);
            }
            if (row == size) sites.union(p, bottom);
            if (row > 1 && isOpen(row-1, col)) {
                q = xyTo1D(row-1, col);
                sites.union(p, q);
                checkSites.union(p, q);
            }
            if (row < size && isOpen(row+1, col)) {
                q = xyTo1D(row+1, col);
                sites.union(p, q);
                checkSites.union(p, q);
            }
            if (col > 1 && isOpen(row, col-1)) {
                q = xyTo1D(row, col-1);
                sites.union(p, q);
                checkSites.union(p, q);
            }
            if (col < size && isOpen(row, col+1)) {
                q = xyTo1D(row, col+1);
                sites.union(p, q);
                checkSites.union(p, q);
            }
        }
    }
    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        indexValid(row, col);
        return state[row-1][col-1];
    }    
    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        int p = xyTo1D(row, col);
        return checkSites.connected(p, TOP);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return counter;
    }
    // does the system percolate?
    public boolean percolates() {
        return sites.connected(TOP, bottom);
    }
    private int xyTo1D(int row, int col) {
        indexValid(row, col);
        return (row-1) * size + col;
    }
    private void indexValid(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException("row index out of bounds");
    }
   
    public static void main(String[] args) {  // test client (optional)
    }
}
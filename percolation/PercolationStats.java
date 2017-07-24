import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class PercolationStats {
    private final double[] res;
    private final int count;
    public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
        if (n < 1 || trials < 1) {
            throw new java.lang.IllegalArgumentException();
        }
        Percolation perc;
        int size;
        count = trials;
        res = new double[trials];
        for (int i = 0; i < trials; i++) {
            perc = new Percolation(n);
            while (!perc.percolates()) {
                int col = StdRandom.uniform(1, n+1);
                int row = StdRandom.uniform(1, n+1);
                perc.open(col, row);
            }
            size = perc.numberOfOpenSites();
            res[i] = (double) size/ (n*n);
        }
        
    }
    public double mean() {                       // sample mean of percolation threshold
        return StdStats.mean(res);
    }
    public double stddev() {                       // sample standard deviation of percolation threshold
        return StdStats.stddev(res);
    }
    public double confidenceLo() {                 // low  endpoint of 95% confidence interval
        return mean()-(1.96*stddev() /Math.sqrt(count));
    }
    public double confidenceHi() {                 // high endpoint of 95% confidence interval
        return mean()+1.96*stddev() /Math.sqrt(count);
    }

    public static void main(String[] args) {       // test client (described below)
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats perc = new PercolationStats(n, trials);
        String confidence = perc.confidenceLo() + ", " + perc.confidenceHi();
        StdOut.println("mean                    = " + perc.mean());
        StdOut.println("stddev                  = " + perc.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
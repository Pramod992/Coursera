package Assignment1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by pramod on 16.6.5.
 */
public class PercolationStats {

    private double[] threshold;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        Percolation percolation;

        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.T = T;
        threshold = new double[T];
        int count;
        for (int i = 0; i < T; i++) {
            count = 0;
            percolation = new Percolation(N);
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(N) + 1;
                int y = StdRandom.uniform(N) + 1;
                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                    count++;
                }
            }
            threshold[i] = (double) count / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }

    public static void main(String[] args) {

    }
}

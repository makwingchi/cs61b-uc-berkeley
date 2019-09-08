package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] fraction;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("argument cannot be <= 0");
        }

        fraction = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            double pct = p.numberOfOpenSites() * 1.0 / (N * N);
            fraction[i] = pct;
        }
    }

    public double mean() {
        return StdStats.mean(fraction);
    }

    public double stddev() {
        return StdStats.stddev(fraction);
    }

    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        double denom = Math.sqrt(fraction.length);
        return mu - 1.96 * sigma / denom;
    }

    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        double denom = Math.sqrt(fraction.length);
        return mu + 1.96 * sigma / denom;
    }

    public static void main(String[] args) {
        // Trial (100*100 grid, 100 experiments)
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(100, 100, pf); // ~0.59
        System.out.println(ps.mean());
    }
}

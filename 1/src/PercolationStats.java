import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by asalle on 23.10.2017.
 */
public class PercolationStats {
    private final double myMean;
    private final double myStdDev;
    private final double confLo;
    private final double confHi;

    // publics
    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        double[] p = new double[trials];

        for (int i = 0; i < trials; ++i) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int x = StdRandom.uniform(1, n+1);
                int y = StdRandom.uniform(1, n+1);
                perc.open(x, y);
            }

            p[i] = perc.numberOfOpenSites()*1.0/(1.0*n*n);
        }

        myMean = StdStats.mean(p);
        myStdDev = StdStats.stddev(p);
        final double error = 1.96 * stddev()/Math.sqrt(p.length);
        confLo = myMean - error;
        confHi = myMean + error;
    }
    public double mean() { // sample mean of percolation threshold
        return myMean;
    }
    public double stddev() { // sample standard deviation of percolation threshold
        return myStdDev;
    }
    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return confLo;
    }
    public double confidenceHi() { // high endpoint of 95% confidence interval
        return confHi;
    }

    public static void main(String[] args) { // test client (described below)
        if (args.length != 2)
            throw new IllegalArgumentException();

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.println("mean: " + percolationStats.mean());
        System.out.println("stddev: " + percolationStats.stddev());
        System.out.println("95% confidence interval: [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}

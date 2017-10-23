import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asalle on 23.10.2017.
 */
public class PercolationStats {
    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        p = new ArrayList<Double>();

        while (trials-- != 0) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int x = StdRandom.uniform(1, n);
                int y = StdRandom.uniform(1, n);
                perc.open(x, y);
            }

            p.add(perc.numberOfOpenSites()*1.0/1.0*n*n);
        }
    }
    public double mean() { // sample mean of percolation threshold

        double[] tempArray = new double[p.size()];
        for (int i = 0; i < p.size(); ++i) {
            tempArray[i] = p.get(i);
        }
        return StdStats.mean(tempArray);
    }
    public double stddev() { // sample standard deviation of percolation threshold

    }
    public double confidenceLo() { // low  endpoint of 95% confidence interval

    }
    public double confidenceHi() { // high endpoint of 95% confidence interval

    }

    public static void main(String[] args) { // test client (described below)
        if (args.length != 2)
            throw new IllegalArgumentException();


    }

    private List<Double> p;
}

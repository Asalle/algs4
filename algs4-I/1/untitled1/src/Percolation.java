import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.time.Clock;

import java.util.concurrent.ThreadLocalRandom;

public class Percolation {
    public Percolation(int n)             // create n-by-n grid, with all sites blocked
    {
        n++;
        mtx = new WeightedQuickUnionUF(n*n);
        isOpen = new int[n][n];
        mtxSize = n-1;
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row < 1 || col < 1)
            throw new IllegalArgumentException();

        if (row > mtxSize || col > mtxSize)
            throw new IllegalArgumentException();

        if (isOpen(row, col))
            return;

        if (col > 1 && isOpen(row, col-1))
        {
            mtx.union(at(row, col-1), at(row, col));
        }

        if (row > 1 && isOpen(row-1, col))
        {
            mtx.union(at(row-1, col), at(row, col));
        }

        if (col < mtxSize && isOpen(row, col+1))
        {
            mtx.union(at(row, col+1), at(row, col));
        }

        if (row < mtxSize && isOpen(row+1, col)) {
            mtx.union(at(row + 1, col), at(row, col));
        }

        isOpen[row][col] = 1;
        openSitesCnt++;
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return isOpen[row][col] == 1;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        for (int i = 1; i <= mtxSize; ++i) {
            if (isOpen(row, col) && isOpen(1, i) && (mtx.find(at(row, col)) == mtx.find(at(1, i)))) {
                return true;
            }
        }

        return false;
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openSitesCnt;
    }

    public boolean percolates()              // does the system percolate?
    {
        for (int i = 1; i <= mtxSize; i++)
            if (isFull(mtxSize, i))
                return true;

        return false;
    }

    private int at(int row, int col)
    {
        return row* mtxSize + col;
    }
    private void print()
    {
        for (int i = 1; i <= mtxSize; ++i) {
            System.out.print(i);
        }
        System.out.print("\n");

        for (int i = 1; i <= mtxSize; ++i) {
            for (int j = 1; j <= mtxSize; ++j) {
                System.out.print(isOpen(i, j) ? "_" : "#");
            }
            System.out.print("\n");
        }

        System.out.print("\n");

        for (int i = 1; i <= mtxSize; ++i) {
            for (int j = 1; j <= mtxSize; ++j) {
                System.out.print(mtx.find(at(i, j)) + " ");
            }
            System.out.print("\n");
        }
    }

    private WeightedQuickUnionUF mtx;
    private int[][] isOpen;
    private int openSitesCnt;
    private int mtxSize;

    public static void main(String[] args)   // test client (optional)
    {
        int num = 7;
        Percolation p = new Percolation(num);
        for (int i = 1; i <= 60; ++i) {
            int rndx = ThreadLocalRandom.current().nextInt(1, num+1);
            int rndy = ThreadLocalRandom.current().nextInt(1, num+1);

            p.open(rndx, rndy);
            if (p.percolates()) {
                System.out.print("YES\n p = " + p.numberOfOpenSites());
                break;
            }
        }
    }
}

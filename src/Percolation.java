/**
 * Created by asalle on 23.10.2017.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] isOpen;
    private int openSitesCnt;
    final private WeightedQuickUnionUF mtx;
    final private int mtxSize;

    public Percolation(int n)             // create n-by-n grid, with all sites blocked
    {
        if (n < 1)
            throw new IllegalArgumentException();
        n++;
        mtx = new WeightedQuickUnionUF((n+1)*(n+1)); // +2 virtual rows: 0 and n+1
        isOpen = new boolean[n+1][n+1];
        mtxSize = n-1;

        for (int i = 0; i < n; ++i) {
            mtx.union(0, at(1, i));
            mtx.union(at(n, 0), at(n-1, i));
        }
        isOpen[0][0] = true;
        isOpen[n][0] = true;
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

        isOpen[row][col] = true;
        openSitesCnt++;
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (row < 1 || row > mtxSize)
            throw new IllegalArgumentException();
        if (col < 1 || col > mtxSize)
            throw new IllegalArgumentException();

        return isOpen[row][col];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row < 1 || row > mtxSize)
            throw new IllegalArgumentException();
        if (col < 1 || col > mtxSize)
            throw new IllegalArgumentException();

        int targetUnionNum = mtx.find(at(row, col));
        return isOpen(row, col) && targetUnionNum == mtx.find(0);
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openSitesCnt;
    }

    public boolean percolates()              // does the system percolate?
    {
        int targetUnionNum = mtx.find(at(mtxSize+1, 0));
        return targetUnionNum == mtx.find(0);
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

    public static void main(String[] args)   // test client (optional)
    {
        int num = 7;
        Percolation p = new Percolation(num);
        for (int i = 1; i <= 60; ++i) {
            int rndx = StdRandom.uniform(1, num+1);
            int rndy = StdRandom.uniform(1, num+1);

            p.open(rndx, rndy);
            if (p.percolates()) {
                p.print();
                System.out.print("YES\n p = " + p.numberOfOpenSites());
                break;
            }
        }
    }
}
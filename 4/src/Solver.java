import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {
    private MinPQ<Board> boardQueue;

    public Solver(Board initial) {
        boardQueue = new MinPQ<Board>();
    }         // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable() {
        return true;
    }            // is the initial board solvable?
    public int moves() {
        return 0;
    }                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution() {
        return new ArrayList<Board>();
    }     // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args) {
        // kek
    } // solve a slider puzzle (given below)
}
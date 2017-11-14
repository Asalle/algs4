import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {
    private MinPQ<Node> boardQueue;

    private class Node {
        private Board searchNode;
        private int movesCnt;
        private Board predec;

        public Node(Board searchNode, int movesCnt, Board predec) {
            this.searchNode = searchNode;
            this.movesCnt = movesCnt;
            this.predec = predec;
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        boardQueue = new MinPQ<>();
        boardQueue.insert(new Node(initial, 0, null));

        Node currentNode = boardQueue.delMin();
        while(!currentNode.searchNode.isGoal()) {
            for (Board neighbor: currentNode.searchNode.neighbors()) {
                Node neighborNode = new Node(neighbor, currentNode.movesCnt+1, currentNode.searchNode);
                boardQueue.insert(neighborNode);
            }
            currentNode = boardQueue.delMin();
        }

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
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)
}
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public final class Solver {
    private final List<Board> answer;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        answer = aStar(initial);
    }

    private final class Node implements Comparable<Node> {
        private final Board searchNode;
        private final int movesCnt;
        private final Board predec;

        public Node(Board searchNode, int movesCnt, Board predec) {
            this.searchNode = searchNode;
            this.movesCnt = movesCnt;
            this.predec = predec;
        }

        @Override
        public int compareTo(Node that) {
            int thisPriority = this.movesCnt + this.searchNode.hamming();
            int thatPriority = that.movesCnt + that.searchNode.hamming();
            if (thisPriority < thatPriority)
                return -1;
            else if (thisPriority > thatPriority)
                return 1;
            else
                return 0;
        }
    }

    private List<Board> aStar(Board initial) {
        MinPQ<Node> boardQueue = new MinPQ<>();
        boardQueue.insert(new Node(initial, 0, null));

        List<Board> answerSequence = new ArrayList<>();

        Node currentNode = boardQueue.delMin();
        while (!currentNode.searchNode.isGoal()) {
            answerSequence.add(currentNode.searchNode);
            for (Board neighbor: currentNode.searchNode.neighbors()) {
                if (neighbor.equals(currentNode.predec))
                    continue;

                Node neighborNode = new Node(neighbor, currentNode.movesCnt+1, currentNode.searchNode);
                boardQueue.insert(neighborNode);
            }
            currentNode = boardQueue.delMin();
        }
        answerSequence.add(currentNode.searchNode);

        return answerSequence;
    }

    public boolean isSolvable() {
        return true;
    }

    public int moves() {
        if (!isSolvable())
            return -1;

        return answer.size()-1;
    }

    public Iterable<Board> solution() {
        return answer;
    }

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
    }
}
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;

public final class Solver {
    private final ArrayList<Board> answer;
    private boolean isSolvable;
    private boolean isGoal;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        isSolvable = true;
        answer = new ArrayList<>();

        if (initial.isGoal()) {
            answer.add(initial);
            isGoal = true;
        } else {
            aStar(initial);
        }
    }

    private final class Node implements Comparable<Node> {
        private final Board searchNode;
        private final int movesCnt;
        private final Node pre;

        public Node(Board searchNode, int movesCnt, Node pre) {
            this.searchNode = searchNode;
            this.movesCnt = movesCnt;
            this.pre = pre;
        }

        @Override
        public int compareTo(Node that) {
            int thisPriority = this.movesCnt + this.searchNode.manhattan();
            int thatPriority = that.movesCnt + that.searchNode.manhattan();
            if (thisPriority < thatPriority)
                return -1;
            else if (thisPriority > thatPriority)
                return 1;
            else
                return 0;
        }
    }

    private void aStar(Board initial) {
        MinPQ<Node> boardQueue = new MinPQ<>();
        MinPQ<Node> twinQueue = new MinPQ<>();

        boardQueue.insert(new Node(initial, 0, null));
        twinQueue.insert(new Node(initial.twin(), 0, null));

//        ArrayList<Board> answerSequence = new ArrayList<>();

        Node currentNode = boardQueue.delMin();
        Node currentTwinNode = twinQueue.delMin();

        while (!currentNode.searchNode.isGoal() && !currentTwinNode.searchNode.isGoal()) {
            for (Board neighbor: currentNode.searchNode.neighbors()) {
                if (currentNode.pre != null && neighbor.equals(currentNode.pre.searchNode))
                    continue;
                Node neighborNode = new Node(neighbor, currentNode.movesCnt+1, currentNode);
                boardQueue.insert(neighborNode);
            }
            currentNode = boardQueue.delMin();

            for (Board twinNeighbor: currentTwinNode.searchNode.neighbors()) {
                if (currentTwinNode.pre != null && twinNeighbor.equals(currentTwinNode.pre.searchNode))
                    continue;
                Node twinNeighborNode = new Node(twinNeighbor, currentTwinNode.movesCnt+1, currentTwinNode);
                twinQueue.insert(twinNeighborNode);
            }
            currentTwinNode = twinQueue.delMin();
        }

        if (currentTwinNode.searchNode.isGoal()) {
            this.isSolvable = false;
            answer.clear();
        } else {
            answer.add(currentNode.searchNode);
            while (!currentNode.searchNode.equals(initial) && currentNode.pre != null) {
                currentNode = currentNode.pre;
                answer.add(currentNode.searchNode);
            }
        }

        Collections.reverse(answer);
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (!isSolvable())
            return -1;

        if (isGoal)
            return 0;

        return answer.size()-1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;

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
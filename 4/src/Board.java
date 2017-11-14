import java.util.ArrayList;
import java.util.List;

public final class Board {
    private final int dimension;
    private final int[][] board;

    public Board(int[][] blocks) {
        dimension = blocks.length;
        board = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    private int[][] generateGoal(int n) {
        int[][] tempBoard = new int[n][n];
        int i = 0;
        while (i < n*n-1) {
            tempBoard[i / n][i % n] = i+1;
            i++;
        }
        return tempBoard;
    }

    private int myAbs(int i) {
        if (i >= 0) {
            return i;
        }

        return -1*i;
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int sum = 0;

        int k = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int item = board[i][j];
                if (item != 0 && item != k + 1) {
                    sum++;
                }
                k++;
            }
        }

        return sum;
    }

    public int manhattan() {
        int sum = 0;
        int k = 1;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int value = board[i][j];
                if (value != 0 && value != k) {
                    int supposedToBeX = value / dimension;
                    int supposedToBeY = value % dimension - 1;
                    int manhattanForCurBlock = myAbs(supposedToBeX - i) + myAbs(supposedToBeY - j);
                    sum += manhattanForCurBlock;
                }
                k++;
            }
        }

        return sum;
    }

    public boolean isGoal() {
        return this.equals(new Board(generateGoal(dimension)));
    }

    public Board twin() {
        int[][] twinContent = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                twinContent[i][j] = board[i][j];
            }
        }

        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        boolean foundOne = false, foundTwo = false;

        outerLoop:
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (foundOne && foundTwo) {
                    int temp = twinContent[i1][j1];
                    twinContent[i1][j1] = twinContent[i2][j2];
                    twinContent[i2][j2] = temp;
                    break outerLoop;
                }
                if (!foundOne && twinContent[i][j] != 0) {
                    i1 = i;
                    j1 = j;
                    foundOne = true;
                    continue;
                }
                if (!foundTwo && twinContent[i][j] != 0) {
                    i2 = i;
                    j2 = j;
                    foundTwo = true;
                }
            }
        }

        return new Board(twinContent);
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (this.dimension() != that.dimension()) {
            return false;
        }

        int size = this.dimension();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] != that.board[i][j])
                    return false;
            }
        }

        return true;
    }

    private Board cloneWithSwitch(int iOld, int jOld, int iNew, int jNew) {
        int[][] cloneContent = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                cloneContent[i][j] = board[i][j];
            }
        }

        int temp = cloneContent[iOld][jOld];
        cloneContent[iOld][jOld] = cloneContent[iNew][jNew];
        cloneContent[iNew][jNew] = temp;

        return new Board(cloneContent);
    }

    public Iterable<Board> neighbors() {
        List<Board> boardList = new ArrayList<Board>();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 0) {
                    if (i > 0) { // up
                        boardList.add(cloneWithSwitch(i, j, i-1, j));
                    }
                    if (j > 0) { // left
                        boardList.add(cloneWithSwitch(i, j, i, j-1));
                    }
                    if (i < dimension-1) { // down
                        boardList.add(cloneWithSwitch(i, j, i+1, j));
                    }
                    if (j < dimension-1) { // right
                        boardList.add(cloneWithSwitch(i, j, i, j+1));
                    }
                }
            }
        }

        return boardList;
    }     // all neighboring boards

    public String toString() {
        StringBuffer boardString = new StringBuffer();
        boardString.append(dimension);
        boardString.append("\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boardString.append(board[i][j] + " ");
            }
            boardString.append("\n");
        }

        return boardString.toString();
    }

    public static void main(String[] args) {
        int[][] a = new int[4][4];
        Board b = new Board(a);
        System.out.println(b.toString());
    }
}


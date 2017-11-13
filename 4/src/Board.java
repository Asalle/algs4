public class Board {
    private Integer dimension;
    private Integer[][] board;

    private int[][] generateGoal(int n) {
        int[][] tempBoard = new int[n][n];
        int i = 0;
        while (i < n*n-1) {
            tempBoard[i/n][i%n] = i+1;
            i++;
        }
        return tempBoard;
    }

    public Board(int[][] blocks) {
        dimension = blocks.length;
        board = new Integer[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return dimension;
    }

//    public int hamming() {
//
//    }                  // number of blocks out of place
//
//    public int manhattan() {
//
//    }                 // sum of Manhattan distances between blocks and goal
//
    public boolean isGoal() {
        return this.equals(new Board(generateGoal(dimension)));
    }
//
//    public Board twin() {
//
//    }                   // a board that is obtained by exchanging any pair of blocks
//
    public boolean equals(Object y) {
        if (this == y)
            return true;

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

//    public Iterable<Board> neighbors() {
//
//    }     // all neighboring boards
//
    public String toString() {
        StringBuffer boardString = new StringBuffer();
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


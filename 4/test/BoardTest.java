import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by mirzaiev on 6.11.2017.
 */
public class BoardTest {
    @Test
    public void testDimension() throws Exception {
        int size = 20;
        int[][] b = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                b[i][j] = 12;
            }
        }
        Board board = new Board(b);

        assert board.dimension() == b.length;
    }

    @Test
    public void testHamming() throws Exception {
        int size = 3;
        int[][] boardContents = new int[size][size];
        boardContents[0][0] = 8;
        boardContents[0][1] = 1;
        boardContents[0][2] = 3;
        boardContents[1][0] = 4;
        boardContents[1][1] = 0;
        boardContents[1][2] = 2;
        boardContents[2][0] = 7;
        boardContents[2][1] = 6;
        boardContents[2][2] = 5;

        Board board = new Board(boardContents);

        assertEquals(board.hamming(), 5);
    }

    @Test
    public void testManhattan() throws Exception {
    }

    @Test
    public void testIsGoal() throws Exception {
        int size = 5;
        int[][] b = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                b[i][j] = 12;
            }
        }
        Board board = new Board(b);

        assert board.isGoal() == false;

        int[][] tempBoard = new int[size][size];
        int i = 0;
        while (i < size*size-1) {
            tempBoard[i/size][i%size] = i+1;
            i++;
        }

        Board goalBoard = new Board(tempBoard);
        assert goalBoard.isGoal() == true;
    }

    @Test
    public void testTwin() throws Exception {
    }

    @Test
    public void testEquals() throws Exception {
        final int size = 5;
        int[][] boardContent = new int[size][size];

        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardContent[i][j] = k+1;
                k++;
            }
        }

        Board a = new Board(boardContent);
        Board b = new Board(boardContent);

        assert a.equals(b) == true;

        boardContent[0][0] = 101;
        Board c = new Board(boardContent);

        assert a.equals(c) == false;
    }

    @Test
    public void testNeighbors() throws Exception {
    }

    @Test
    public void testToString() throws Exception {
    }

    @Test
    public void testMain() throws Exception {
    }

}
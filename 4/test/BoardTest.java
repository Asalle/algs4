import org.testng.annotations.Test;

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
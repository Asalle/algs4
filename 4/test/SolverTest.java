import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by mirzaiev on 14.11.2017.
 */
public class SolverTest {
    @Test
    public void testIsSolvable() throws Exception {
        int size = 3;
        int[][] boardContents = new int[size][size];
        boardContents[0][0] = 1;
        boardContents[0][1] = 2;
        boardContents[0][2] = 3;
        boardContents[1][0] = 4;
        boardContents[1][1] = 5;
        boardContents[1][2] = 6;
        boardContents[2][0] = 8;
        boardContents[2][1] = 7;
        boardContents[2][2] = 0;

        Board board = new Board(boardContents);
        Solver solver = new Solver(board);
        assertEquals(solver.isSolvable(), false);
    }

    @Test
    public void testZeroMoves() throws Exception {
    }

    @Test
    public void testSolution() throws Exception {
    }

    @Test
    public void testAstar() throws Exception {
    }
}
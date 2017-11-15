import edu.princeton.cs.algs4.Point2D;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointSETTest {
    @Test
    public void testIsEmpty() throws Exception {
        PointSET set = new PointSET();
        assertEquals(set.isEmpty(), true);

        set.insert(new Point2D(2, 4));
        assertEquals(set.isEmpty(), false);
    }

    @Test
    public void testSize() throws Exception {
        PointSET set = new PointSET();
        assertEquals(set.size(), 0);

        set.insert(new Point2D(1, 2));
        assertEquals(set.size(), 1);
    }

    @Test
    public void testInsert() throws Exception {
        PointSET set = new PointSET();
        Point2D point2D = new Point2D(1, 2);
        set.insert(point2D);
        assertEquals(set.contains(point2D), true);
    }

    @Test
    public void testContains() throws Exception {
        PointSET set = new PointSET();
        Point2D point2D = new Point2D(1, 2);
        set.insert(point2D);
        assertEquals(set.contains(point2D), true);

        Point2D notInSet = new Point2D(32, 2);
        assertEquals(set.contains(notInSet), false);
    }

    @Test
    public void testRange() throws Exception {
    }

    @Test
    public void testNearest() throws Exception {
    }

}
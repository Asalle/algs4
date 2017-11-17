import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class KdTreeTest {
    @Test
    public void testIsEmpty() throws Exception {
        KdTree set = new KdTree();
        assertEquals(set.isEmpty(), true);

        set.insert(new Point2D(2, 4));
        assertEquals(set.isEmpty(), false);
    }

    @Test
    public void testSize() throws Exception {
        KdTree set = new KdTree();
        assertEquals(set.size(), 0);

        set.insert(new Point2D(1, 2));
        assertEquals(set.size(), 1);
    }

    @Test
    public void testInsert() throws Exception {
        KdTree set = new KdTree();
        Point2D point2D = new Point2D(1, 2);
        set.insert(point2D);
        assertEquals(set.contains(point2D), true);
    }

    @Test
    public void testContains() throws Exception {
        KdTree set = new KdTree();
        Point2D point2D = new Point2D(1, 2);
        set.insert(point2D);
        assertEquals(set.contains(point2D), true);

        Point2D notInSet = new Point2D(32, 2);
        assertEquals(set.contains(notInSet), false);

        KdTree tree = new KdTree();
        tree.insert(new Point2D(4, 2));
        Point2D a = new Point2D(8, 3);
        tree.insert(a);
        Point2D b = new Point2D(10, 5);
        tree.insert(b);
        tree.insert(new Point2D(13, 7));
        tree.insert(new Point2D(6, -1));
        tree.insert(new Point2D(8, 0));
        tree.insert(new Point2D(7, 1));
        tree.insert(new Point2D(9, 0));

        assertEquals(tree.contains(new Point2D(7, 1)), true);
        assertEquals(tree.contains(new Point2D(9, 0)), true);
        assertEquals(tree.contains(new Point2D(6, -1)), true);
    }

    @Test
    public void testRange() throws Exception {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(4, 2));
        Point2D a = new Point2D(8, 3);
        tree.insert(a);
        Point2D b = new Point2D(10, 5);
        tree.insert(b);
        tree.insert(new Point2D(13, 7));

        RectHV bound = new RectHV(7, 2, 11, 5);
        Set<Point2D> pointsInsideRect = new HashSet<>();
        for (Point2D point: tree.range(bound)) {
            pointsInsideRect.add(point);
        }

        assertEquals(pointsInsideRect.size(), 2);
        assertEquals(pointsInsideRect.contains(a), true);
        assertEquals(pointsInsideRect.contains(b), true);
    }

    @Test
    public void testNearest() throws Exception {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(4, 2));
        Point2D a = new Point2D(8, 3);
        tree.insert(a);
        Point2D b = new Point2D(10, 5);
        tree.insert(b);
        tree.insert(new Point2D(13, 7));

        Point2D point = new Point2D(7, 3);
        assertEquals(tree.nearest(point).equals(a), true);
    }

    @Test
    public void testInsertMany() throws Exception {
        List<Point2D> pointList = new LinkedList<>();
        KdTree tree = new KdTree();
        int size = 100;
        for (int i = 0; i < size; i++) {
            Point2D point = new Point2D(StdRandom.uniform(size), StdRandom.uniform(size));
            tree.insert(point);
            pointList.add(point);
        }

        for (Point2D point: pointList) {
            assertEquals(tree.contains(point), true);
        }

        int size5 = 500;
        for (int i = 0; i < size; i++) {
            Point2D point = new Point2D(StdRandom.uniform(100, size5), StdRandom.uniform(100, size5));
            assertEquals(tree.contains(point), false);
        }
    }
}
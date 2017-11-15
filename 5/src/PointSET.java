import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class PointSET {
    private SET<Point2D> pointSet;
    private double maxX;
    private double maxY;

    public PointSET() {
        pointSet = new SET<>();
        maxX = 0;
        maxY = 0;
    }

    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    public int size() {
        return pointSet.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (!pointSet.contains(p)) {
            pointSet.add(p);

            if (p.x() > maxX)
                maxX = p.x();

            if (p.y() > maxY)
                maxY = p.y();
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return pointSet.contains(p);
    }

    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, maxX+1);
        StdDraw.setYscale(0, maxY+1);
        for (Point2D p : pointSet) {
            p.draw();
        }
        StdDraw.show();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<Point2D> insideList = new ArrayList<>();

        for (Point2D point: pointSet) {
            if(rect.contains(point)) {
                insideList.add(point);
            }
        }

        return insideList;
    }
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (pointSet.isEmpty()) {
            return null;
        }

        Point2D minDistancePoint = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
        for (Point2D point: pointSet) {
            double currentDistance = p.distanceTo(point);
            double minDistance = p.distanceSquaredTo(minDistancePoint);
            if (Double.compare(minDistance, currentDistance) > 0) {
                minDistancePoint = point;
            }
        }

        return minDistancePoint;
    }

    public static void main(String[] args) {
        PointSET set = new PointSET();
        set.insert(new Point2D(2, 3));
        set.insert(new Point2D(5, 6));
        set.insert(new Point2D(1, 10));

        set.draw();
    }
}
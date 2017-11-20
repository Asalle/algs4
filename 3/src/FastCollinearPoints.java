import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int segmentsCnt;

    public FastCollinearPoints(Point[] points) {
        checkConstructorArguments(points);

        if (points.length == 0) {
            segments = new LineSegment[0];
            return;
        }

        Point[] pointsCopy = new Point[points.length];
        System.arraycopy(points, 0, pointsCopy, 0, points.length);

        segments = findCollinear(pointsCopy);
        segmentsCnt = segments.length;
    }

    private LineSegment[] findCollinear(Point[] points) {
        LineSegment[] containsNullSegments = new LineSegment[points.length];
        int segCnt = 0;

        Point[] pointsCopy = new Point[points.length];
        System.arraycopy(points, 0, pointsCopy, 0, points.length);

        for (Point origin : points) {
            Arrays.sort(pointsCopy, origin.slopeOrder());

            int l = 0, r = l;
            while (l < pointsCopy.length && r < pointsCopy.length) {
                if (equ(origin.slopeTo(pointsCopy[l]), origin.slopeTo(pointsCopy[r]))) {
                    r++;
                } else {
                    segCnt = addSegment(l, r, containsNullSegments, pointsCopy, origin, segCnt);
                    l = r;
                }
            }
            // add final points
            segCnt = addSegment(l, r, containsNullSegments, pointsCopy, origin, segCnt);
        }

        LineSegment[] returnSeg = new LineSegment[segCnt];
        System.arraycopy(containsNullSegments, 0, returnSeg, 0, segCnt);
        return returnSeg;
    }

    private int addSegment(int l, int r, LineSegment[] containsNullSegments, Point[] points, Point origin, int segCnt) {
        if (r - l > 2) {
            Point[] candidateSegment = new Point[r - l+1];
            System.arraycopy(points, l, candidateSegment, 0, r - l);
            candidateSegment[r-l] = origin;
            Arrays.sort(candidateSegment);

            if (origin.compareTo(candidateSegment[0]) == 0) {
                containsNullSegments[segCnt] = new LineSegment(candidateSegment[0], candidateSegment[r - l]);
                segCnt++;
            }
        }

        return segCnt;
    }

    private boolean equ(double a, double b) {
        return Double.compare(a, b) == 0;
    }

    private void checkConstructorArguments(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point p: points) {
            if (p == null)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public int numberOfSegments() {
        return segmentsCnt;
    }    // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] returnSegments = new LineSegment[segments.length];
        System.arraycopy(segments, 0, returnSegments, 0, segmentsCnt);

        return returnSegments;
    }                  // the line segments



    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


}
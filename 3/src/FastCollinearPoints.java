import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int segmentsCnt;

    public FastCollinearPoints(Point[] points) {
        checkConstructorArguments(points);
        segments = new LineSegment[0];
        if (points.length == 0) {
            return;
        }

        Point[] pointsCopy = new Point[points.length];
        System.arraycopy(points, 0, pointsCopy, 0, points.length);

        segments = findCollinear(pointsCopy);
        segmentsCnt = segments.length;
    }    // finds all line segments containing 4 or more points

    private LineSegment[] findCollinear(Point[] points) {
        LineSegment[] candidateSegments = new LineSegment[points.length];
        int segInd = 0;
        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];
            int otherPointsCnt = points.length - i - 1;
            Point[] otherPoints = new Point[otherPointsCnt];
            System.arraycopy(points, i+1, otherPoints, 0, otherPointsCnt);
            segInd = getSegmentsCollinearToOrigin(origin, otherPoints, candidateSegments, segInd);
        }

        System.out.println(segInd);

        LineSegment[] noNullSegments = new LineSegment[segInd];
        System.arraycopy(candidateSegments, 0, noNullSegments, 0, segInd);
        return noNullSegments;
    }

    private int getSegmentsCollinearToOrigin(Point origin, Point[] points, LineSegment[] alreadyInSegments, int segInd) {
        Arrays.sort(points, origin.slopeOrder());

//        Double[] slopes = new Double[points.length];
//        int ind = 0;
//        for (Point p: points) {
//            System.out.println(origin.slopeTo(p));
//            slopes[ind++] = origin.slopeTo(p);
//        }

        int sameCnt = 0;
        for (int i = 1; i < points.length; i++) {
            if (equ(origin.slopeTo(points[i]), origin.slopeTo(points[i-1]))) {
                sameCnt++;
            }
            else
            {
                segInd = insertLineSegment(sameCnt, i, origin, alreadyInSegments, segInd, points);
                sameCnt = 0;
            }
        }

        if (sameCnt >= 2) {

            segInd = insertLineSegment(sameCnt, points.length, origin, alreadyInSegments, segInd, points);
        }

        return segInd;
    }

    private int insertLineSegment(int sameCnt, int size, Point origin, LineSegment[] alreadyInSegments, int segInd, Point[] points)
    {
        if (sameCnt >= 2) {
            int end = size - 1;
            int start = end - sameCnt;

            Point[] collinearPoints = new Point[sameCnt + 1]; // and origin
            System.arraycopy(points, start, collinearPoints, 0, sameCnt);
            collinearPoints[collinearPoints.length - 1] = origin;

            Arrays.sort(collinearPoints);
            assert collinearPoints[0] != null;
            assert collinearPoints[collinearPoints.length-1] != null;
            LineSegment candidateSegment = new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length-1]);

            boolean alreadyIn = false;
            for (LineSegment ls: alreadyInSegments) {
                if (ls == candidateSegment)
                    alreadyIn = true;
            }

            if (!alreadyIn && candidateSegment != null) {
                assert candidateSegment != null;
                alreadyInSegments[segInd] = candidateSegment;
                segInd++;
            }
        }

        return segInd;
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
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


}
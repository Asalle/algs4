import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private final LineSegment[] segments;
    private int segmentsCnt;

    // types
    private class PointnSlope {
        // members
        private final Point point;
        private final double slope;

        private class PointnSlopeComparator implements Comparator<PointnSlope> {
            public int compare(PointnSlope a, PointnSlope b) {
                if (Double.compare(a.slope, b.slope) < 0)
                    return -1;
                if (Double.compare(a.slope, b.slope) == 0)
                    return 0;

                return 1;
            }
        }

        // public
        PointnSlope(Point point, double slope) {
            this.point = point;
            this.slope = slope;
        }

        public Comparator<PointnSlope> comparator() {
            return new PointnSlopeComparator();
        }
    }

    public FastCollinearPoints(Point[] points) {
        checkContructorArguments(points);

        segments = new LineSegment[points.length/2];

        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];
            int candidateCnt = points.length-i-1;
            PointnSlope[] slopes = new PointnSlope[candidateCnt];
            int[] collinears = new int[candidateCnt];

            for (int j = i+1; j < points.length; j++) {
                slopes[j-(i+1)] = new PointnSlope(points[j], origin.slopeTo(points[j]));
            }

            Arrays.sort(slopes, slopes[0].comparator());

            collinears[0] = 1;
            for (int j = 1; j < candidateCnt; j++) {
                if (Double.compare(slopes[j].slope, slopes[j-1].slope) == 0)
                    collinears[j] = collinears[j]+1;
                else
                    collinears[j] = 1;
            }

            int begin = 0, end = 0;
            for (int j = 1; j < candidateCnt; j++) {
                if (collinears[j] > collinears[j-1]) {
                    end = j;
                } else {
                    begin = j;
                    end = j;
                }

                if (end - begin >= 3) {
                    Point[] collinearPoints = new Point[end - begin];
                    for (int k = begin; k < end; k++) {
                        collinearPoints[k - begin] = slopes[k].point;
                    }

                    Arrays.sort(collinearPoints);

                    segments[segmentsCnt] = new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length-1]);
                    segmentsCnt++;
                }
            }
        }
    }    // finds all line segments containing 4 or more points

    private void checkContructorArguments(Point[] points) {
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
}
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;
    private int segmentsCnt;

    public BruteCollinearPoints(Point[] points) {
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

        Point[] pointsCopy = new Point[points.length];
        System.arraycopy(points, 0, pointsCopy, 0, points.length);

        LineSegment[] tempsegments = new LineSegment[pointsCopy.length];
        Arrays.sort(pointsCopy, Point::compareTo);
        for (int i = 0; i < pointsCopy.length; i++) {
            for (int j = i+1; j < pointsCopy.length; j++) {
                for (int k = j+1; k < pointsCopy.length; k++) {
                    for (int r = k+1; r < pointsCopy.length; r++) {
                        if (Double.compare(pointsCopy[i].slopeTo(pointsCopy[j]), pointsCopy[i].slopeTo(pointsCopy[k])) == 0 &&
                                Double.compare(pointsCopy[i].slopeTo(pointsCopy[r]), pointsCopy[i].slopeTo(pointsCopy[j])) == 0) {
                            Point[] potentials = new Point[4];
                            potentials[0] = pointsCopy[i];
                            potentials[1] = pointsCopy[j];
                            potentials[2] = pointsCopy[k];
                            potentials[3] = pointsCopy[r];

                            LineSegment potentialNewSegment = new LineSegment(potentials[0], potentials[3]);
                            boolean alreadyPresent = false;
                            for (LineSegment ls: tempsegments) {
                                if (ls == null)
                                    continue;
                                if (ls == potentialNewSegment)
                                    alreadyPresent = true;
                            }

                            if (!alreadyPresent) {
                                tempsegments[segmentsCnt] = new LineSegment(pointsCopy[i], pointsCopy[r]);
                                segmentsCnt++;
                            }
                        }
                    }
                }
            }
        }

        segments = new LineSegment[segmentsCnt];
        System.arraycopy(tempsegments, 0, segments, 0, segmentsCnt);
    }   // finds all line segments containing 4 points

//    private void print()
//    {
//        for (LineSegment ls: segments) {
//            System.out.println(ls);
//        }
//    }

    public int numberOfSegments() {
        return segmentsCnt;
    }        // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] returnSegments = new LineSegment[segments.length];
        System.arraycopy(segments, 0, returnSegments, 0, segmentsCnt);

        return returnSegments;
    }                // the line segments
}
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

        LineSegment[] tempsegments = new LineSegment[points.length];
        Arrays.sort(points, Point::compareTo);
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                for (int k = j+1; k < points.length; k++) {
                    for (int r = k+1; r < points.length; r++) {
                        if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[k])) == 0 &&
                                Double.compare(points[i].slopeTo(points[r]), points[i].slopeTo(points[j])) == 0) {
                            Point[] potentials = new Point[4];
                            potentials[0] = points[i];
                            potentials[1] = points[j];
                            potentials[2] = points[k];
                            potentials[3] = points[r];

                            LineSegment potentialNewSegment = new LineSegment(potentials[0], potentials[3]);
                            boolean alreadyPresent = false;
                            for (LineSegment ls: tempsegments) {
                                if (ls == null)
                                    continue;
                                if (ls.toString().equals(potentialNewSegment.toString()))
                                    alreadyPresent = true;
                            }

                            if (!alreadyPresent) {
                                tempsegments[segmentsCnt] = new LineSegment(points[i], points[r]);
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
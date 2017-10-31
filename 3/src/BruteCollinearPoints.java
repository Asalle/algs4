public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point p: points) {
            if (p == null)
                throw new IllegalArgumentException();
        }

        // TODO check for repeating points

    }   // finds all line segments containing 4 points

    public int numberOfSegments() {

    }        // the number of line segments

    public LineSegment[] segments() {

    }                // the line segments
}
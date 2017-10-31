public class FastCollinearPoints {
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point p: points) {
            if (p == null)
                throw new IllegalArgumentException();
        }
    }    // finds all line segments containing 4 or more points

    public           int numberOfSegments()        // the number of line segments
    public LineSegment[] segments()                // the line segments
}
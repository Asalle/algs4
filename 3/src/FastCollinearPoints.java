//import java.util.Arrays;
//
//public class FastCollinearPoints {
//
//    private final LineSegment[] segments;
//    private int segmentsCnt;
//
//    public FastCollinearPoints(Point[] points) {
//        checkConstructorArguments(points);
//        if (points.length > 0) {
//            Arrays.sort(points, points[0].slopeOrder());
//            LineSegment[] tempSegments = new LineSegment[points.length];
//
//            for (int i = 0; i < points.length; i++) {
//                Point origin = points[i];
//                int candidateCnt = points.length - i - 1;
//                int[] collinears = new int[candidateCnt+1];
//
//                collinears[0] = 1;
//                for (int j = i+1; j <= candidateCnt; j++) {
////                    System.out.println(i + " " + j + " " + points.length + " " + candidateCnt);
//                    if (Double.compare(origin.slopeTo(points[j]), origin.slopeTo(points[j - 1])) == 0)
//                        collinears[j-i] = collinears[j-i-1] + 1;
//                    else
//                        collinears[j-i] = 1;
//                }
//
//                for ()
//
//                int begin = 0, end;
//                for (int j = 1; j < candidateCnt; j++) {
//                    if (collinears[j] > collinears[j - 1]) {
//                        end = j;
//                    } else {
//                        begin = j;
//                        end = j;
//                    }
//
//                    if (end - begin >= 3) {
//                        Point[] collinearPoints = new Point[end - begin];
//                        for (int k = begin+i; k < end+i; k++) {
//                            collinearPoints[k - begin - i] = points[k];
//                        }
//
//                        Arrays.sort(collinearPoints);
//
//                        tempSegments[segmentsCnt] = new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length - 1]);
//                        segmentsCnt++;
//                    }
//                }
//            }
//
//            segments = new LineSegment[segmentsCnt];
//            System.arraycopy(tempSegments, 0, segments, 0, segmentsCnt);
//        }
//        else {
//            segments = new LineSegment[0];
//        }
//    }    // finds all line segments containing 4 or more points
//
//    private void checkConstructorArguments(Point[] points) {
//        if (points == null)
//            throw new IllegalArgumentException();
//
//        for (Point p: points) {
//            if (p == null)
//                throw new IllegalArgumentException();
//        }
//
//        for (int i = 0; i < points.length; i++) {
//            for (int j = i+1; j < points.length; j++) {
//                if (points[i].compareTo(points[j]) == 0) {
//                    throw new IllegalArgumentException();
//                }
//            }
//        }
//    }
//
//    public int numberOfSegments() {
//        return segmentsCnt;
//    }    // the number of line segments
//
//    public LineSegment[] segments() {
//        LineSegment[] returnSegments = new LineSegment[segments.length];
//        System.arraycopy(segments, 0, returnSegments, 0, segmentsCnt);
//
//        return returnSegments;
//    }                  // the line segments
//}
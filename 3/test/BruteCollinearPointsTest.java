import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by mirzaiev on 3.11.2017.
 */
public class BruteCollinearPointsTest {
    @Test
    public void testNumberOfSegments() throws Exception {
        Path filePath = Paths.get("/home/mirzaiev/me/coursera/algs4/tests/3/collinear/equidistant.txt");
        Scanner textReader = new Scanner(filePath);
        int n = textReader.nextInt();
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int a = textReader.nextInt();
            int b = textReader.nextInt();
            points[i] = new Point(a, b);
        }

        BruteCollinearPoints bruteForce = new BruteCollinearPoints(points);
        System.out.println(bruteForce.numberOfSegments());
        for (LineSegment ls: bruteForce.segments()) {
            System.out.println(ls);
        }
//        bruteForce.print();
//
//        for (Point p: points) {
//            p.draw();
//        }
    }

    @Test
    public void testSegments() throws Exception {

    }

}
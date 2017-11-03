import org.testng.annotations.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import static org.testng.Assert.*;

/**
 * Created by mirzaiev on 3.11.2017.
 */
public class PointTest {
    @Test
    public void testSlopeTo() throws Exception {
        Point a = new Point(21000, 10000);
        Point b = new Point(2000, 10000);

        assertEquals(new Double(a.slopeTo(b)).compareTo(0.0), 0);

        Point c = new Point(21001, 5678);
//        System.out.println(a.slopeTo(c) + " " + a.slopeTo(b) + " kek");
        assertNotEquals(new Double(a.slopeTo(c)).compareTo(0.0), 0);
    }

    @Test
    public void testCompareTo() throws Exception {
    }

    @Test
    public void testSlopeOrder() throws Exception {
    }

}
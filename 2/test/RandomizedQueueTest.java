import edu.princeton.cs.algs4.In;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.princeton.cs.algs4.StdRandom.uniform;
import static org.testng.Assert.*;

/**
 * Created by mirzaiev on 28.10.2017.
 */
public class RandomizedQueueTest {
    @Test
    public void testIsEmpty() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        assertEquals(queue.isEmpty(), true);

        queue.enqueue(2);
        assertEquals(queue.isEmpty(), false);

        queue.dequeue();
        assertEquals(queue.isEmpty(),true);
    }

    @Test
    public void testSize() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        assertEquals(queue.size(), 0);

        queue.enqueue(2);
        assertEquals(queue.size(), 1);

        queue.dequeue();
        assertEquals(queue.size(), 0);
    }

    @Test
    public void testEnqueue() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        assertEquals(queue.isEmpty(), true);

        Integer item = 2;
        queue.enqueue(item);
        assertEquals(queue.dequeue(), item);
    }

    @Test
    public void testDequeue() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        assertEquals(queue.isEmpty(), true);

        Integer item = 12;
        queue.enqueue(item);
        assertEquals(queue.dequeue(), item);
        assertEquals(queue.isEmpty(), true);
    }

    @Test
    public void testSample() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(106);
        queue.enqueue(8);

        Integer in = queue.sample();

        boolean yes = false;
        int s = queue.size();
        for (int i = 0; i < s; ++i) {
            if (queue.dequeue() == in)
                yes = true;
        }

        assertEquals(yes, true);
    }

    @Test
    public void testIterator() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        final int constr = 19;
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i < constr; ++i) {
            int item = uniform(constr);
            integerList.add(item);
            queue.enqueue(item);
        }

        int i = 0;
        for (Integer integ: queue) {
            assertEquals(integ, integerList.get(i++));
        }
    }

    @Test
    public void testExtending() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        final int constr = 100;
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i < constr; ++i) {
            int item = uniform(constr);
            integerList.add(item);
            queue.enqueue(item);
        }

        int i = 0;
        for (Integer integ: queue) {
            assertEquals(integ, integerList.get(i++));
        }
    }

    @Test
    public void testShrinking() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        final int constr = 100;
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i < constr; ++i) {
            int item = uniform(constr);
            integerList.add(item);
            queue.enqueue(item);
        }

        for (int i = 0; i < constr/4*3; i++) {
            queue.dequeue();
            integerList.remove(0);
        }

        int i = 0;
        for (Integer integ: queue) {
            assertEquals(integ, integerList.get(i++));
        }
    }
}
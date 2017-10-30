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

    // dequeue is random
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
            Integer act = queue.dequeue();
            if (act == in)
                yes = true;
        }

        assertEquals(yes, true);
    }

    @Test
    public void testIterator() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        final int constr = 1;
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
    public void testEnqDeq() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        assertEquals(queue.isEmpty(), true);
        assertEquals(queue.size(), 0);
        assertEquals(queue.size(), 0);
        queue.enqueue(26);
        assertEquals(queue.dequeue(), new Integer(26));
        assertEquals(queue.isEmpty(), true);
        assertEquals(queue.size(), 0);
        queue.enqueue(18);
        assertEquals(queue.dequeue(), new Integer(18));
    }
}
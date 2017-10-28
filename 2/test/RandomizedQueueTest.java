import org.testng.annotations.Test;

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
    }

    @Test
    public void testEnqueue() throws Exception {
    }

    @Test
    public void testDequeue() throws Exception {
    }

    @Test
    public void testSample() throws Exception {
    }

    @Test
    public void testIterator() throws Exception {
    }

}
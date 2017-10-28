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

        Integer in = queue.sample();
        System.out.println(in);

        boolean yes = false;
        for (int i = 0; i < queue.size(); ++i) {
            if (queue.dequeue() == in)
                yes = true;
        }

//        assertEquals(yes, true);
    }

    @Test
    public void testIterator() throws Exception {
    }

}
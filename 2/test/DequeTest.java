import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by mirzaiev on 27.10.2017.
 */
public class DequeTest {
    @Test
    public void testIsEmpty() throws Exception {
        Deque<String> deq = new Deque<String>();
        assertEquals(deq.isEmpty(), true);
    }

    @Test
    public void testSize() throws Exception {
    }

    @Test
    public void testAddFirst() throws Exception {
        Deque<String> deq = new Deque<String>();
        assertEquals(deq.isEmpty(), true);
        assertEquals(deq.size(), 0);

        String item = "Hello World!";
        deq.addFirst(item);

        assertEquals(deq.isEmpty(), false);
        assertEquals(deq.size(), 1);
    }

    @Test
    public void testAddLast() throws Exception {
        Deque<String> deq = new Deque<String>();
        assertEquals(deq.isEmpty(), true);
        assertEquals(deq.size(), 0);

        String item = "Hello World!";
        deq.addLast(item);

        assertEquals(deq.isEmpty(), false);
        assertEquals(deq.size(), 1);
    }

    @Test
    public void testRemoveFirst() throws Exception {
        Deque<String> deq = new Deque<String>();
        assertEquals(deq.isEmpty(), true);
        assertEquals(deq.size(), 0);

        String item = "Hello World!";
        deq.addLast(item);
        deq.removeFirst();
    }

}
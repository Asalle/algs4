import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
        assertEquals(deq.isEmpty(), true);
        assertEquals(deq.size(), 0);
    }

    @Test
    public void testRemoveLast() throws Exception {
        Deque<String> deq = new Deque<String>();
        assertEquals(deq.isEmpty(), true);
        assertEquals(deq.size(), 0);

        String item = "Hello World!";
        deq.addFirst(item);
        deq.removeLast();
        assertEquals(deq.isEmpty(), true);
        assertEquals(deq.size(), 0);
    }

    @Test
    public void testIteratorManual() throws Exception {
        Deque<String> deq = new Deque<String>();
        deq.addFirst("Hello");
//        deq.addLast("world");
//        assertEquals(deq.size(), 2);
//
//        Iterator<String> it = deq.iterator();
//
//        assertEquals(it.next(), "world");
//        assertEquals(it.next(), "Hello");
    }

    @Test
    public void testIteratorForeach() throws Exception {
        Deque<String> deq = new Deque<String>();
        List<String> testList = new ArrayList<String>();
        testList.add("Hello");
        testList.add("World");

        for (String item: testList) {
            deq.addLast(item);
        }
        assertEquals(deq.size(), 2);

        int i = 0;
        for (String item: deq) {
            assertEquals(item, testList.get(i));
            i++;
        }
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testIteratorOutOfBound() throws Exception {
        Deque<String> deq = new Deque<String>();
        deq.addFirst("Hello");
        deq.addFirst("world");
        assertEquals(deq.size(), 2);

        Iterator<String> it = deq.iterator();

        assertEquals(it.next(), "world");
        assertEquals(it.next(), "Hello");
        it.next();
    }
}
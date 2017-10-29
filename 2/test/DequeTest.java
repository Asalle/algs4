import org.testng.annotations.Test;

import java.util.*;

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
        deq.addLast("world");
        assertEquals(deq.size(), 2);

        Iterator<String> it = deq.iterator();

        assertEquals(it.next(), "Hello");
//        assertEquals(it.next(), "world");
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

        List<String> testListFirst = new ArrayList<String>();
        testListFirst.add("I'm");
        testListFirst.add("Asa");

        for (String item: testListFirst) {
            deq.addFirst(item);
        }

        assertEquals(deq.size(), testList.size() + testListFirst.size());

        List<String> expec = new ArrayList<String>();
        Collections.reverse(testListFirst);
        expec.addAll(testListFirst);
        expec.addAll(testList);

        int i = 0;
        for (String item: deq) {
            assertEquals(item, expec.get(i));
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
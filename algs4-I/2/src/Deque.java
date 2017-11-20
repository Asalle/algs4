import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by mirzaiev on 27.10.2017.
 */

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Node next;
        private Node prev;
        private final Item val;

        Node(Node n, Node p, Item v)
        {
            next = n;
            prev = p;
            val = v;
        }

        public void setNext(Node n) {
            next = n;
        }

        public void setPrev(Node p) {
            prev = p;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node cur;
        DequeIterator() {
            cur = head;
        }

        public boolean hasNext() {
            return cur != null;
        }

        public Item next() {
            if (cur != null) {
                Item now = cur.val;
                cur = cur.next;
                return now;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Deque() {

    } // construct an empty deque

    public boolean isEmpty() {
        return head == null && tail == null;
    }                 // is the deque empty?

    public int size() {
        return size;
    }              // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node temp = new Node(head, null, item);
        if (head != null) {
            head.setPrev(temp);
        }
        head = temp;
        if (tail == null)
            tail = temp;
        size++;
    }         // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node temp = new Node(null, tail, item);
        if (tail != null) {
            tail.setNext(temp);
        }
        tail = temp;
        size++;
        if (head == null)
            head = temp;
    }           // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item temp = head.val;
        if (head == tail)
        {
            tail = null;
        }

        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
        return  temp;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item temp = tail.val;
        if (head == tail) {
            head = null;
        }
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        size--;

        return temp;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }         // return an iterator over items in order from front to end

    public static void main(String[] args)   // unit testing (optional)
    {
        // this line is intentionally left blank
    }
}

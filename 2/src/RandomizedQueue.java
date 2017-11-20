import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by mirzaiev on 27.10.2017.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Node prev;
        private Node next;
        private final Item val;

        Node(Node p, Node n, Item v) {
            prev = p;
            next = n;
            val = v;
        }
    }

    private class RQIterator implements Iterator<Item> {
        private Node cur;
        // public
        public RQIterator() {
            cur = goToRandomNode();
        }

        public boolean hasNext() {
            return cur != null;
        }

        public Item next() {
            if (hasNext()) {
                Item temp = cur.val;
                cur = cur.next;
                return temp;
            }
            else
                throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    };

    // public
    public RandomizedQueue() {
    } // construct an empty randomized queue

    public boolean isEmpty() {
        return size() == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return size;
    }                        // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node candidate = new Node(tail, null, item);
        if (tail == null)
            tail = candidate;
        else {
            tail.next = candidate;
            tail = candidate;
        }
        if (head == null)
            head = tail;
        size++;
    }           // add the item

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node randomNode = goToRandomNode();
        if (randomNode.next != null)
            randomNode.next.prev = randomNode.prev;
        if (randomNode.prev != null)
            randomNode.prev.next = randomNode.next;

        if (size() == 1) {
            head = null;
            tail = null;
        }

        if (randomNode == head) {
            head = head.next;
        }

        if (randomNode == tail) {
            tail = tail.prev;
        }

        size--;
        return randomNode.val;
   }                    // remove and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node randomNode = goToRandomNode();
        return randomNode.val;
    } // return a random item (but do not remove it)

    private Node goToNode(int nodeNumber) {
        Node cur = head;
        if (nodeNumber <= size()/2) {
            cur = head;
            while (nodeNumber > 0) {
                cur = cur.next;
                nodeNumber--;
            }
        } else {
            cur = tail;
            int ind = size()-nodeNumber-1;
            while (ind > 0) {
                cur = cur.prev;
                ind--;
            }
        }
        assert (cur != null);
        return cur;
    }

    private Node goToRandomNode() {
        int randInd = StdRandom.uniform(size());
        return goToNode(randInd);
    }

    public Iterator<Item> iterator() {
        return new RQIterator();
    }    // return an independent iterator over items in random order

    public static void main(String[] args) {
        // this line is intentionally left blank
    }  // unit testing (optional)
}

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.StdRandom.uniform;

/**
 * Created by mirzaiev on 27.10.2017.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    int head;
    int tail;

    private class RQIterator implements Iterator<Item> {
        private int cur;

        // public
        public RQIterator() {
            cur = head;
        }

        public boolean hasNext() {
            return head - tail > 1;
        }

        public Item next() {
            if (hasNext())
                return items[++cur];
            else
                throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    };

    private void resize(int cnt) {
        Item[] temp = (Item[]) new Object[cnt+1];
        for(int i = 1; i <= size(); ++i) {
            temp[i] = items[head+i];
        }
        cnt = size();
        head = 0;
        tail = cnt;
        items = temp;
    }

    // public
    public RandomizedQueue() {
        head = 0;
        tail = 0;
        items = (Item[]) new Object[20];
    } // construct an empty randomized queue

    public boolean isEmpty() {
        return head >= tail || tail == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return tail - head;
    }                        // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (items.length - tail <= 1) {
            resize(items.length*2);
        }
        items[++tail] = item;
    }           // add the item

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size() < items.length/4) {
            resize(items.length/2);
        }
        return (Item) items[++head];
    }                    // remove and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = uniform(size());
        return items[i+1];
    }                     // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
        return new RQIterator();
    }    // return an independent iterator over items in random order

    public static void main(String[] args) {

    }  // unit testing (optional)
}

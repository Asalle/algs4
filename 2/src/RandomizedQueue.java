import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.StdRandom.uniform;

/**
 * Created by mirzaiev on 27.10.2017.
 */

public class RandomizedQueue<Item> {/*implements Iterable<Item> {*/
    private Item[] items;
    int head;
    int tail;

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
        if (size() >= items.length/4) { // TODO amortized add
//            items = (Item[]) new Object(items.length)
        }
        items[++tail] = item;
    }           // add the item

    public Item dequeue() { // TODO impl actual queue not stack
        if (isEmpty())
            throw new NoSuchElementException();

        return (Item) items[++head];
    }                    // remove and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = uniform(size());
        return items[i+1];
    }                     // return a random item (but do not remove it)

//    public Iterator<Item> iterator() {
//
//    }    // return an independent iterator over items in random order

    public static void main(String[] args) {

    }  // unit testing (optional)
}

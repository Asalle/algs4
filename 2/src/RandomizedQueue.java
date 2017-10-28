import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.StdRandom.uniform;

/**
 * Created by mirzaiev on 27.10.2017.
 */

public class RandomizedQueue<Item> {/*implements Iterable<Item> {*/
    private Item[] items;
    int cur;

    // public
    public RandomizedQueue() {
        cur = -1;
        items = (Item[]) new Object[20];
    } // construct an empty randomized queue

    public boolean isEmpty() {
        return cur == -1;
    }                 // is the randomized queue empty?

    public int size() {
        return cur+1;
    }                        // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (cur >= items.length/4) { // TODO amortized add
//            items = (Item[]) new Object(items.length)
        }
        items[++cur] = item;
    }           // add the item

    public Item dequeue() { // TODO impl actual queue not stack
        if (isEmpty())
            throw new NoSuchElementException();

        return (Item) items[cur--];
    }                    // remove and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = uniform(0, size());
        return items[i];
    }                     // return a random item (but do not remove it)

//    public Iterator<Item> iterator() {
//
//    }    // return an independent iterator over items in random order

    public static void main(String[] args) {

    }  // unit testing (optional)
}

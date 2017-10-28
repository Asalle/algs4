import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by mirzaiev on 27.10.2017.
 */

public class RandomizedQueue<Item> {/*implements Iterable<Item> {*/
    private Item[] items;
    int cur;

    // public
    public RandomizedQueue() {
        cur = -1;
        items = new Item[20];
    } // construct an empty randomized queue

    public boolean isEmpty() {
        return cur == -1;
    }                 // is the randomized queue empty?

    public int size() {
        return items.length;
    }                        // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        items[++cur] = item;
    }           // add the item

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        return items[cur--];
    }                    // remove and return a random item
//
//    public Item sample() {
//
//    }                     // return a random item (but do not remove it)
//
//    public Iterator<Item> iterator() {
//
//    }    // return an independent iterator over items in random order

    public static void main(String[] args) {

    }  // unit testing (optional)
}

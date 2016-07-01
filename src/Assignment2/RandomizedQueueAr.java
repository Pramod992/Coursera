package Assignment2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by pramod on 16.22.4.
 */
public class RandomizedQueueAr<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    public RandomizedQueueAr() {
        size = 0;
        items = (Item[]) new Object[1];
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size] = item;
        size++;
    }

    private void resize(int resize) {
        Item[] temp = (Item[]) new Object[resize];
        for (int i = 0; i < size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int index = StdRandom.uniform(size);
        Item item = items[index];

        if (index != size - 1) {
            items[index] = items[size - 1];
        }
        items[size - 1] = null;
        size--;

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = (StdRandom.uniform(size));
        return items[index];
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int current = 0;
        private int[] shuffledIndexes = new int[size];

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (current == 0) {
                for (int i = 0; i < size; i++) {
                    shuffledIndexes[i] = i;
                }
                StdRandom.shuffle(shuffledIndexes);
            }
            if (current >= size || size() == 0) {
                throw new java.util.NoSuchElementException();
            }
            return items[shuffledIndexes[current++]];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }
}

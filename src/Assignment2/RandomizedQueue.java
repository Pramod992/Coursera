package Assignment2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by Pramod on 16.22.4.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;

    public RandomizedQueue() {
    }

    private class Node {
        private Item item;
        private Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node current;

        if (first == null) {
            current = new Node();
            current.item = item;
            first = current;
            last = current;
            last.next = null;
        } else {
            current = last;
            last = new Node();
            last.item = item;
            current.next = last;
            last.next = null;
        }
        last.next = null;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node current = first;
        Node temp;
        int randValue = StdRandom.uniform(size);

        for (int i = 0; i < randValue; i++) {
            if (current.next != last && current.next != null) {
                current = current.next;
            }
        }
        if (current == first) {
            temp = first;
            first = null;
        } else if (current.next == last) {
            temp = last;
            current.next = null;
            last = current;
        } else {
            temp = current.next;
            current.next = temp.next;
        }
        size--;
        if(isEmpty()){
            last = null;
        }
        return temp.item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(size);

        Node current = first;
        for (int i = 0; i < index; i++) {
            if (current.next != null) {
                current = current.next;
            }
        }
        return current.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int current = 0;
        private Node temp = first;
        private Item[] randomArray = (Item[]) new Object[size];

        public boolean hasNext() {

            return current < size;
        }

        public Item next() {
            if (hasNext()) {
                if (current == 0) {
                    for (int i = 0; i < size; i++) {
                        randomArray[i] = temp.item;
                        temp = temp.next;
                    }
                    StdRandom.shuffle(randomArray);
                }
            }
            if (current >= size || size() == 0) {
                throw new java.util.NoSuchElementException();
            }
            return randomArray[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
    }
}

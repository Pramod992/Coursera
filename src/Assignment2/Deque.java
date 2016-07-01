package Assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pramod on 16.22.4.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Item getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public void setNext(Node nextNode) {
            this.next = nextNode;
        }

        public void setPrev(Node prevNode) {
            this.prev = prevNode;
        }
    }

    public Deque() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        Node node = first;
        int count = 1;
        while (node != last) {
            count++;
            node = node.next;
        }

        return count;
    }

    public void addFirst(Item item) {

        if (item == null) {
            throw new NullPointerException();
        }

        Node node = new Node();
        node.item = item;

        if (first != null) {
            node.next = first;
            first.prev = node;
            node.prev = null;
            first = node;
        } else {
            first = node;
            last = node;
            node.next = null;
            node.prev = null;
        }
    }

    public void addLast(Item item) {

        if (item == null) {
            throw new NullPointerException();
        }

        Node node = new Node();
        node.item = item;

        if (last != null) {
            node.prev = last;
            last.next = node;
            node.next = null;
            last = node;
        } else {
            last = node;
            first = node;
            node.prev = null;
            node.next = null;
        }
    }

    public Item removeFirst() {
        Item item = null;
        if (first == null) {
            throw new NoSuchElementException();
        }
        item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        return item;
    }

    public Item removeLast() {
        Item item = null;
        if (last == null) {
            throw new NoSuchElementException();
        }
        item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        return item;

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque();
        deque.addFirst(10);
        deque.addFirst(10);
        deque.addFirst(10);

        deque.size();
        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }
}
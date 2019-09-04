package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> firstNode;
    private Node<Item> lastNode;
    private int size = 0;

    private class Node<Item> {
        private Item currentElement;
        private Node<Item> nextElem;
        private Node<Item> prevElem;

        public void setCurrentElement(Item currentElement) {
            this.currentElement = currentElement;
        }

        public void setNextElem(Node<Item> nextElem) {
            this.nextElem = nextElem;
        }

        public void setPrevElem(Node<Item> prevElem) {
            this.prevElem = prevElem;
        }

        public Node(Item currentElement, Node<Item> nextElem, Node<Item> prevElem) {
            this.currentElement = currentElement;
            this.nextElem = nextElem;
            this.prevElem = prevElem;
        }

        public Item getCurrentElement() {
            return currentElement;
        }

        public Node<Item> getNextElem() {
            return nextElem;
        }

        public Node<Item> getPrevElem() {
            return prevElem;
        }
    }

    // construct an empty deque
    public Deque() {
        lastNode = new Node<Item>(null, firstNode, null);
        firstNode = new Node<Item>(null, null, lastNode);
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (firstNode == null) return true;
        else return false;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("No arg");
        Node<Item> next = firstNode;
        next.setCurrentElement(item);
        firstNode = new Node<Item>(null, null, next);
        next.setPrevElem(firstNode);
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("No arg");
        Node<Item> prev = lastNode;
        prev.setCurrentElement(item);
        lastNode = new Node<Item>(null, prev, null);
        prev.setNextElem(lastNode);
        size++;
    }

    ;

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("The deque is empty");
        Item item = firstNode.getCurrentElement();
        firstNode.getNextElem().setPrevElem(null);
        firstNode = firstNode.getNextElem();
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Node<Item> temp = lastNode;
        if (isEmpty()) throw new NoSuchElementException("The deque is empty");
        if (firstNode == lastNode) {
            firstNode = null;
            lastNode = null;
            size = 0;
            return lastNode.getCurrentElement();
        }
        Item item = lastNode.getCurrentElement();
        lastNode = temp.getPrevElem();
        lastNode.setNextElem(null);
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = firstNode;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = (Item) current.getCurrentElement();
            if (item == null) throw new NoSuchElementException();
            current = current.getNextElem();
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque deque = new Deque();
        System.out.println(deque.size());
        deque.addFirst("ewewewew");
        deque.addLast(456456);
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
    }
}

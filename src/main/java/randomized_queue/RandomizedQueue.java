package randomized_queue;

import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int capacity;
    private int endOfQueue;
    private Random random = new Random();
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue(){
        capacity = 0;
        endOfQueue = 0;
        queue = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return capacity == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return capacity;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null) throw new NullPointerException();
        System.out.println(queue.length);
        if(capacity == queue.length) {
            resize(queue.length * 2);
            queue[capacity++] = item;
            endOfQueue = capacity;
        }
    }

    private void resize(int newSize) {
        Item[] newQueue = Arrays.copyOfRange(queue, 0, newSize );
        queue = newQueue;
        System.out.println(queue.length);
    }

    // remove and return a random item
    public Item dequeue(){
        if(size() == 0) throw new UnsupportedOperationException();
        if(capacity <= queue.length/4) resize(queue.length/2);
        int ind = random.nextInt(endOfQueue);
        Item item = queue[ind];
        queue[ind] = queue[--endOfQueue];
        queue[endOfQueue] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        int ind = random.nextInt(endOfQueue);
        return queue[ind];
    }

     private class RanQueueIterator implements Iterator<Item> {

        private Item[] shuffledArray;

        private int current = 0;

        public RanQueueIterator() {
            shuffledArray = queue.clone();
            shuffle(shuffledArray);
        }

        @Override
        public boolean hasNext() {
            return current < queue.length;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return shuffledArray[current++];
        }


        public void shuffle(Item[] array) {
            int n = array.length;
            for (int i = 0; i < n; i++) {
                // choose index uniformly in [i, n-1]
                int r = i + (int) (Math.random() * (n - i));
                Item swap = array[r];
                array[r] = array[i];
                array[i] = swap;
            }
        }

    }
    @Override
    public Iterator<Item> iterator() {
        return (Iterator<Item>) new RanQueueIterator();
    }



    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        // adding 10 elements
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in queue: " + randomizedQueue.size() + "\n");
            System.out.println("EndOfQueue - " + new RandomizedQueue().endOfQueue);
        }

        System.out.print("\nIterator test:\n[");
        for (Integer elem: randomizedQueue)
            System.out.print(elem + " ");
        System.out.println("]\n");

        // removing 10 elements
        for (int i = 0; i < 10; i++) {
            System.out.println("Removed element: " + randomizedQueue.dequeue());
            System.out.println("Current number of elements in queue: " + randomizedQueue.size() + "\n");
        }

    }
}

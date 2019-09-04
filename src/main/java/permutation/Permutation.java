package permutation;

import edu.princeton.cs.algs4.StdIn;
import randomized_queue.RandomizedQueue;

import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        String str;
        try {
            do{
                str = StdIn.readString();
                randomizedQueue.enqueue(str);
            }while(str!=null);
        }catch (NoSuchElementException e){
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                System.out.println(randomizedQueue.dequeue());
            }
        }
    }
}

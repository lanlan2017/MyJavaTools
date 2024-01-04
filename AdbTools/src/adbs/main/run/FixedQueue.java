package adbs.main.run;

import java.util.LinkedList;

public class FixedQueue<T> {
    private final int MAX_SIZE;
    private LinkedList<T> queue;

    public FixedQueue(int maxSize) {
        MAX_SIZE = maxSize;
        queue = new LinkedList<>();
    }

    public boolean add(T item) {
        if (queue.size() < MAX_SIZE) {
            queue.add(item);
            return true;
        } else {
            return false;
        }
    }

    // public boolean offer(T item) {
    //     if (queue.size() < MAX_SIZE) {
    //         queue.add(item);
    //         return true;
    //     } else {
    //         // Remove the head of the queue to make space for a new element
    //         if (!queue.isEmpty()) {
    //             queue.removeFirst();
    //         }
    //         queue.add(item);
    //         return true;
    //     }
    // }

    public T offer(T item) {
        T poll = null;
        if (queue.size() < MAX_SIZE) {
            queue.add(item);
            // return true;
        } else {
            // Remove the head of the queue to make space for a new element
            // if (!queue.isEmpty()) {
            //     queue.removeFirst();
            // }
            if (!queue.isEmpty()) {
                // queue.removeFirst();
                poll=queue.poll();
            }
            queue.add(item);
            // return true;
        }
        return poll;
    }

    public T remove() {
        return queue.removeFirst();
    }

    public T poll() {
        return queue.poll();
    }

    public T element() {
        return queue.getFirst();
    }

    public T peek() {
        return queue.peek();
    }

}
package adbs.main.run;

import java.util.LinkedList;

public class FixedQueue2<T> {
    private final int MAX_SIZE;
    private LinkedList<T> queue;

    public FixedQueue2(int maxSize) {
        MAX_SIZE = maxSize;
        queue = new LinkedList<>();
    }

    public boolean add(T element) {
        if (queue.size() < MAX_SIZE) {
            queue.addLast(element);
            return true;
        } else {
            return false; // Queue is full, cannot add more elements
        }
    }

    public boolean offer(T element) {
        if (queue.size() < MAX_SIZE) {
            queue.addLast(element);
            return true;
        } else {
            return false; // Queue is full, cannot add more elements
        }
    }

    public T remove() {
        if (queue.isEmpty()) {
            return null; // Queue is empty, cannot remove elements
        } else {
            return queue.removeFirst(); // Remove and return the element at the front of the queue
        }
    }

    public T poll() {
        if (queue.isEmpty()) {
            return null; // Queue is empty, cannot remove elements
        } else {
            return queue.poll(); // Remove and return the element at the front of the queue, or null if the queue is empty.
        }
    }

    public T element() {
        if (queue.isEmpty()) {
            return null; // Queue is empty, cannot retrieve elements from the front of the queue.
        } else {
            return queue.getFirst(); // Retrieve, but do not remove, the element at the front of the queue.
        }
    }

    public T peek() {
        if (queue.isEmpty()) {
            return null; // Queue is empty, cannot retrieve elements from the front of the queue.
        } else {
            return queue.peek(); // Retrieve, but do not remove, the element at the front of the queue, or null if the queue is empty.
        }
    }
}
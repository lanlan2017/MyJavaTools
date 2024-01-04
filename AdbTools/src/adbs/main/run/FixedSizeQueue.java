// package adbs.main.run;
//
// import java.util.*;
// import java.util.concurrent.locks.Condition;
// import java.util.concurrent.locks.ReentrantLock;
//
// public class FixedSizeQueue<T> implements Queue<T> {
//     private final LinkedList<T> queue;
//     private final int maxSize;
//     private final ReentrantLock lock = new ReentrantLock();
//     private final Condition queueCondition = lock.newCondition();
//
//     public FixedSizeQueue(int maxSize) {
//         this.queue = new LinkedList<>();
//         this.maxSize = maxSize;
//     }
//
//     @Override
//     public int size() {
//         return 0;
//     }
//
//     @Override
//     public boolean isEmpty() {
//         return false;
//     }
//
//     @Override
//     public boolean contains(Object o) {
//         return false;
//     }
//
//     @Override
//     public Iterator<T> iterator() {
//         return null;
//     }
//
//     @Override
//     public Object[] toArray() {
//         return new Object[0];
//     }
//
//     @Override
//     public <T1> T1[] toArray(T1[] a) {
//         return null;
//     }
//
//     @Override
//     public synchronized void add(T item) {
//         while (!queue.isEmpty() && queue.size() == maxSize) {
//             try {
//                 wait();
//             } catch (InterruptedException e) {
//                 Thread.currentThread().interrupt();
//                 throw new RuntimeException(e);
//             }
//         }
//         if (queue.isEmpty()) {
//             queue.addFirst(item);
//             notifyAll();
//         } else {
//             queue.addLast(item);
//         }
//     }
//
//     @Override
//     public boolean remove(Object o) {
//         return false;
//     }
//
//     @Override
//     public boolean containsAll(Collection<?> c) {
//         return false;
//     }
//
//     @Override
//     public boolean addAll(Collection<? extends T> c) {
//         return false;
//     }
//
//     @Override
//     public boolean removeAll(Collection<?> c) {
//         return false;
//     }
//
//     @Override
//     public boolean retainAll(Collection<?> c) {
//         return false;
//     }
//
//     @Override
//     public void clear() {
//
//     }
//
//     @Override
//     public synchronized boolean offer(T item) {
//         if (queue.size() == maxSize) {
//             return false;
//         } else {
//             queue.addLast(item);
//             return true;
//         }
//     }
//
//     @Override
//     public synchronized T remove() {
//         if (queue.isEmpty()) {
//             throw new NoSuchElementException();
//         } else if (queue.size() == 1) {
//             T item = queue.removeFirst();
//             notifyAll();
//             return item;
//         } else {
//             return queue.removeLast();
//         }
//     }
//
//     @Override
//     public synchronized T poll() {
//         if (queue.isEmpty()) {
//             return null;
//         } else if (queue.size() == 1) {
//             T item = queue.pollFirst();
//             notifyAll();
//             return item;
//         } else {
//             return queue.pollLast();
//         }
//     }
//
//     @Override
//     public synchronized T element() {
//         if (queue.isEmpty()) {
//             throw new NoSuchElementException();
//         } else if (queue.size() == 1) {
//             return queue.getFirst();
//         } else {
//             return queue.getLast();
//         }
//     }
//
//     @Override
//     public synchronized T peek() {
//         if (queue.isEmpty()) {
//             return null;
//         } else if (queue.size() == 1) {
//             return queue.getFirst();
//         } else {
//             return queue.getLast();
//         }
//     }
// }
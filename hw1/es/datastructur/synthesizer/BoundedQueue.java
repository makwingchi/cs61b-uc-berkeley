package es.datastructur.synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity(); // return size of the buffer

    int fillCount(); // return number of items currently in the buffer

    void enqueue(T x); // add item x to the end

    T dequeue(); // delete and return item from the front

    T peek(); // return (but do not delete) item from the front

    Iterator<T> iterator();

    default boolean isEmpty() {
        // Is the buffer empty?
        return this.fillCount() == 0;
    }

    default boolean isFull() {
        // Is the buffer full?
        return this.fillCount() == this.capacity();
    }
}

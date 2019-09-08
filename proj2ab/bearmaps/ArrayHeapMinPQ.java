package bearmaps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private int heapSize;
    private ArrayList<Node> MinPQ;
    private HashSet<T> items;

    private class Node {
        private T item;
        private double priority;

        private Node(T i, double p) {
            item = i;
            priority = p;
        }
    }

    public ArrayHeapMinPQ() {
        MinPQ = new ArrayList<>();
        heapSize = 0;
        items = new HashSet<>();
    }

    private void swim(int k) {
        while (k >= 0 && compare(k, (k-1)/2)) {
            exch(k, (k-1)/2);
            k = (k-1)/2;
        }
    }

    private void sink(int k) {
        while (k * 2 + 1 < heapSize) {
            int j = 2 * k + 1;
            if (j < heapSize - 1 && !compare(j, j + 1)) {
                j += 1;
            }
            if (compare(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        Node tempI = MinPQ.get(i);
        Node tempJ = MinPQ.get(j);
        MinPQ.set(i, tempJ);
        MinPQ.set(j, tempI);
    }

    private boolean compare(int i, int j) {
        double thisP = MinPQ.get(i).priority;
        double parentP = MinPQ.get(j).priority;
        return thisP < parentP;
    }

    public void add(T item, double priority) {
        if (items.contains(item)) {
            throw new IllegalArgumentException("Item already exists.");
        }

        heapSize += 1; items.add(item);

        Node newNode = new Node(item, priority);
        MinPQ.add(newNode);
        swim(heapSize - 1);
    }

    public boolean contains(T item) {
        return items.contains(item);
    }

    public T getSmallest() {
        if (heapSize == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return MinPQ.get(0).item;
    }

    public T removeSmallest() {
        if (heapSize == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }

        exch(0, heapSize - 1);
        T returnedItem = MinPQ.get(heapSize - 1).item;
        items.remove(returnedItem);
        MinPQ.remove(heapSize - 1);
        heapSize -= 1;
        sink(0);
        return returnedItem;
    }

    public int size() {
        return heapSize;
    }

    public void changePriority(T item, double priority) {
        if (!items.contains(item)) {
            throw new NoSuchElementException("Item does not exist.");
        }

        int idx = 0;
        while (idx < heapSize) {
            Node thisNode = MinPQ.get(idx);
            if (thisNode.item.equals(item)) {
                thisNode.priority = priority;
                break;
            }
            idx += 1;
        }
        sink(idx);
        swim(idx);
    }
}

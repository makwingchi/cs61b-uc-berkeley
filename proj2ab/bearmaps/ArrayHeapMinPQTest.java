package bearmaps;

import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        assertEquals(0, heap.size());
        heap.add("Dog", 1);
        heap.add("Cat", 2);
        heap.add("Horse", 3);
        heap.add("Fish", 0);
        heap.add("Tiger", 2.5);
        assertEquals(5, heap.size());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("Dog", 1);
        heap.add("Cat", 2);
        heap.add("Horse", 3);
        heap.add("Fish", 0);
        assertTrue(heap.contains("Dog"));
        assertFalse(heap.contains("Flower"));
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("Dog", 4);
        heap.add("Cat", 2);
        heap.add("Horse", 3);
        heap.add("Fish", 1);
        heap.add("Tiger", 0);
        assertEquals("Tiger", heap.getSmallest());
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("Dog", 4);
        heap.add("Cat", 2);
        heap.add("Horse", 3);
        heap.add("Fish", 1);
        heap.add("Tiger", 0);
        heap.removeSmallest();
        assertEquals("Fish", heap.getSmallest());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("Dog", 1);
        heap.add("Cat", 2);
        heap.add("Horse", 3);
        heap.add("Fish", 4);
        heap.changePriority("Fish", 1.5);
        heap.changePriority("Cat", 1.0);
        heap.changePriority("Dog", 0);
        assertEquals("Dog", heap.getSmallest());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElement() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("Dog", 1);
        heap.add("Cat", 3);
        heap.add("Fish", 2);
        heap.changePriority("Horse", 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("Dog", 1);
        heap.add("Cat", 3);
        heap.add("Dog", 2);
    }
}

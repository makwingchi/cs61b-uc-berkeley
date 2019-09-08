package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class testPercolation {
    @Test
    public void testPerc() {
        // Initialize a 4*4 grid
        Percolation perc = new Percolation(4);
        // Open three sites
        perc.open(0,0);
        perc.open(2,0);
        perc.open(1,0);
        // Test number of open sites
        assertEquals(3, perc.numberOfOpenSites());
        // Test percolates
        assertFalse(perc.percolates());
        // Test isFull
        assertTrue(perc.isFull(1,0));
        assertTrue(perc.isFull(2,0));
        assertFalse(perc.isFull(2,1));

        // Open another two sites to make the system percolate
        perc.open(2,1);
        perc.open(3,1);
        // Test isFull
        assertTrue(perc.isFull(2,1));
        assertTrue(perc.isFull(3,1));
        assertFalse(perc.isFull(3,3));
        // Test number of open sites
        assertEquals(5, perc.numberOfOpenSites());
        // Test isOpen
        assertTrue(perc.isOpen(0, 0));
        assertTrue(perc.isOpen(2, 1));
        assertFalse(perc.isOpen(0, 3));
        // Test percolates
        assertTrue(perc.percolates());

        // Test backwash
        perc.open(3, 3);
        assertFalse(perc.isFull(3,3));
    }
}
